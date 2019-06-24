package com.yeafel.net.chat.demo01;

import java.io.*;
import java.net.Socket;

/**
 * @author Yeafel
 * 创建客户端： 发送数据 + 接收数据
 * 写出数据  ：输出流
 * 读取数据  ：输入流
 *
 输入流 与 输出流在同一个线程内 应该 独立处理，彼此独立

 * 2019/6/24 11:13
 * Do or Die,To be a better man!
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost",9999);
        //控制台输入流
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String info = console.readLine();

            //输出流
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
            dataOutputStream.writeUTF(info);
            dataOutputStream.flush();
            //输入流
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            String msg = dataInputStream.readUTF();
            System.out.println(msg);
        }

    }
}
