package com.yeafel.net.chat.demo01;

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
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket client = serverSocket.accept();

        //写出数据
        //输入流
        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
        String msg = dataInputStream.readUTF();
        System.out.println(msg);

        //输出流
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
        dataOutputStream.writeUTF("服务器-->"+msg);
        dataOutputStream.flush();

    }
}
