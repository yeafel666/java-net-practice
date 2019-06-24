package com.yeafel.net.chat.demo02;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Yeafel
 * 接收线程
 * 2019/6/24 11:29
 * Do or Die,To be a better man!
 */
public class Receive implements Runnable{
    //客户端的输入流
    private DataInputStream dataInputStream;
    //线程标识
    private boolean isRunning = true;

    public Receive() {
    }

    public Receive(Socket client) {
        try {
            dataInputStream = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            //e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll(dataInputStream);
        }
    }

    /**
     * 接收数据
     * @return
     */
    public String receive() {
        String msg = "";
        try {
            msg = dataInputStream.readUTF();
        } catch (IOException e) {
            //e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll();
        }
        return msg;
    }

    @Override
    public void run() {
        //线程体
        while (isRunning) {
            System.out.println(receive());
        }
    }
}
