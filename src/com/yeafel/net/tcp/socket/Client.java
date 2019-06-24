package com.yeafel.net.tcp.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Yeafel
 * 1、创建客户端  必须指定服务器+端口 此时就在连接
 * 2、接收数据 + 发送数据
 * Socket(String host,int port)
 * 2019/6/24 9:29
 * Do or Die,To be a better man!
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //1、创建客户端  必须指定服务器+端口 此时就在连接
        Socket client = new Socket("localhost",8888);
        //2、接收数据
     /*   BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(client.getInputStream())
        );
        String echo = bufferedReader.readLine(); //阻塞式的方法
        System.out.println(echo);*/

        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
        String echo = dataInputStream.readUTF();
        System.out.println(echo);
    }
}
