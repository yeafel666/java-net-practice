package com.yeafel.net.chat.demo04;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Yeafel
 * 发送数据 线程
 * 2019/6/24 11:28
 * Do or Die,To be a better man!
 */
public class Send implements Runnable{
    //控制台的输入流
    private BufferedReader console;
    //管道的输出流
    private DataOutputStream dataOutputStream;
    //控制线程
    private boolean isRunning = true;
    //名称
    private String name;

    public Send() {
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public Send(Socket client,String name) {
        this();
        try {
            dataOutputStream = new DataOutputStream(client.getOutputStream());
            this.name = name;
            send(this.name);
        } catch (IOException e) {
            //e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll(dataOutputStream,console);
        }
    }

    //1、从控制台接收数据
    private String getMsgFromConsole() {
        try {
            return console.readLine();
        } catch (IOException e) {
//            e.printStackTrace();
            return "";
        }
    }
    /**
     * 1、从控制台接收数据
     * 2、发送数据
     */
    public void send(String msg) {
        if (msg != null && !msg.equals("")) {
            try {
                dataOutputStream.writeUTF(msg);
                dataOutputStream.flush(); //强制刷新
            } catch (IOException e) {
                //e.printStackTrace();
                isRunning = false;
                CloseUtil.closeAll(dataOutputStream,console);
            }
        }
    }

    @Override
    public void run() {
        //线程体
        while (isRunning) {
            send(getMsgFromConsole());
        }
    }
}
