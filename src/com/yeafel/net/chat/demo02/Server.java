package com.yeafel.net.chat.demo02;

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

        //如果这样接收多个客户端，存在先后顺序，必须等前一个客户端通信完成后，才可以进行下一个客户端通信，这样不好。如何更好的实现呢？看demo03
        while (true) {
            Socket client = serverSocket.accept();

            //写出数据
            //输入流
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());

            while (true) {
                String msg = dataInputStream.readUTF();
                System.out.println(msg);

                //输出流
                DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
                dataOutputStream.writeUTF("服务器-->"+msg);
                dataOutputStream.flush();
            }
        }



    }
}
