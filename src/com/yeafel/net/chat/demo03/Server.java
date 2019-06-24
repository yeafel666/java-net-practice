package com.yeafel.net.chat.demo03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Yeafel
 * 创建服务器
 * 写出数据  ：输出流
 * 读取数据  ：输入流
 * 2019/6/24 11:13
 * Do or Die,To be a better man!
 */
public class Server {

    public static void main(String[] args) throws IOException {
        new Server().start();
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);

        //如果这样接收多个客户端，存在先后顺序，必须等前一个客户端通信完成后，才可以进行下一个客户端通信，这样不好。如何更好的实现呢？看demo03
        while (true) {
            Socket client = serverSocket.accept();
            MyChannel channel = new MyChannel(client);
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

        public MyChannel(Socket client) {
            try {
                dataInputStream = new DataInputStream(client.getInputStream());
                dataOutputStream = new DataOutputStream(client.getOutputStream());
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
            }
        }

        @Override
        public void run() {
            while (isRunning) {
                send(receive());
            }
        }
    }

}
