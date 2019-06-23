package com.yeafel.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author Yeafel
 * 服务端
 * 1、创建服务端+端口
 * 2、准备接收容器
 * 3、封装成包
 * 4、接收数据
 * 5、分析数据
 * 6、释放资源
 * 2019/6/23 16:16
 * Do or Die,To be a better man!
 */
public class MyServer {

    public static void main(String[] args) throws IOException {
        //1、创建服务端+端口
        DatagramSocket server = new DatagramSocket(8888);
        //2、准备接收容器
        byte[] container = new byte[1024];
        //3、封装成包  DataGramPacket(byte[] buf, int length)
        DatagramPacket packet = new DatagramPacket(container, container.length);
        //4、接收数据
        server.receive(packet);
        //5、分析数据
        byte[] data = packet.getData();
        int len = packet.getLength();
        System.out.println(new String(data,0,len));
        //6、释放资源
        server.close();
    }
}
