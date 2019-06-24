package com.yeafel.net.chat.demo04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yeafel
 * 创建服务器
 * 写出数据  ：输出流
 * 读取数据  ：输入流
 * 2019/6/24 11:13
 * Do or Die,To be a better man!
 */
public class Server {

    private List<MyChannel> all = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Server().start();
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);

        //如果这样接收多个客户端，存在先后顺序，必须等前一个客户端通信完成后，才可以进行下一个客户端通信，这样不好。如何更好的实现呢？看demo03
        while (true) {
            Socket client = serverSocket.accept();
            MyChannel channel = new MyChannel(client);
            all.add(channel);//统一管理
            new Thread(channel).start();  //一条道路
        }
    }

    /**
     * 一个客户端一条道路
     * 1、输入流
     * 2、输出流
     * 3、接收数据
     * 4、发送数据
     */
    private class MyChannel implements Runnable {
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        private boolean isRunning = true;
        private String name;

        public MyChannel(Socket client) {
            try {
                dataInputStream = new DataInputStream(client.getInputStream());
                dataOutputStream = new DataOutputStream(client.getOutputStream());

                this.name = dataInputStream.readUTF();
                this.send("欢迎您进入聊天室");
                sendOthers(this.name+"进入了聊天室",true);
            } catch (IOException e) {
                //e.printStackTrace();
                CloseUtil.closeAll(dataInputStream,dataOutputStream);
                isRunning = false;
            }

        }

        /**
         * 读取数据
         * @return
         */
        private String receive() {
            String msg = null;
            try {
                msg = dataInputStream.readUTF();
            } catch (IOException e) {
                //e.printStackTrace();
                CloseUtil.closeAll(dataInputStream);
                isRunning = false;
                all.remove(this); //移除自身
            }
            return msg;
        }

        /**
         * 发送数据
         */
        private void send(String msg) {
            if (msg == null || msg.equals("")) {
                return;
            }
            try {
                dataOutputStream.writeUTF(msg);
                dataOutputStream.flush();
            } catch (IOException e) {
                //e.printStackTrace();
                CloseUtil.closeAll(dataOutputStream);
                isRunning = false;
                all.remove(this); //移除自身
            }
        }

        /**
         * 发送给其他客户端
         */
        private void sendOthers(String msg, boolean sys) {
            //是否为私聊 约定@XXX为私聊
            System.out.println(msg);
            if (msg.startsWith("@") && msg.contains(":")) {  //私聊
                //获取name
                String name = msg.substring(1,msg.indexOf(":"));
                String content = msg.substring(msg.indexOf(":")+1);
                for (MyChannel other : all) {
                    if (other.name.equals(name)) {
                        other.send(this.name+"对您悄悄的说："+content);
                    }
                }
            } else {
                //遍历容器
                for (MyChannel other : all) {
                    if (other == this) {
                        continue;
                    }
                    if (sys) {
                        //系统信息
                        other.send("系统信息：" + msg);
                    } else {
                        //发送其他客户端
                        other.send(this.name+"对所有人说："+msg);
                    }
                }
            }

        }

        @Override
        public void run() {
            while (isRunning) {
                sendOthers(receive(),false);
            }
        }
    }

}
