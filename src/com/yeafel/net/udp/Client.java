package com.yeafel.net.udp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @author Yeafel
 * 客户端
 * 1、创建客户端+端口
 * 2、准备数据      double --> 字节数组    字节数组输出流
 * 3、打包(发送的地点以及端口)
 * 4、发送
 * 5、释放
 * 2019/6/23 16:16
 * Do or Die,To be a better man!
 */
public class Client {

    public static void main(String[] args) throws IOException {
        //1、创建客户端+端口
        DatagramSocket client = new DatagramSocket(6666);
        //2、准备数据
        double num = 59.12;
        byte[] data = convert(num);
        //3、打包(发送的地点以及端口)  UDP协议是非面向连接的，是数据去找服务器。不是连接的时候去找服务器。
        DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 8888));
        //4、发送
        client.send(packet);
        //5、释放
        client.close();

    }

    /**
     * 字节数组 数据源  + Data输出流
     * @param num
     * @return
     */
    public static byte[] convert(double num) throws IOException {
        byte[] data;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeDouble(num);
        dataOutputStream.flush();

        //获取数据
        data = byteArrayOutputStream.toByteArray();

        return data;
    }


}
