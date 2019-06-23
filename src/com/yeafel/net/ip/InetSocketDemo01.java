package com.yeafel.net.ip;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @author Yeafel
 * 封装端口：在InetAddress基础上+端口
 *
 * 1、创建对象
 * 2、方法：getAddress()  getHostName
 * 2019/6/23 17:42
 * Do or Die,To be a better man!
 */
public class InetSocketDemo01 {
    public static void main(String[] args) throws UnknownHostException {

        //第一种方式，sun公司程序员封装好了,自动调用InetAddress.getByName("127.0.0.1")
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",9999);

        //第二种构造方式，不推荐
        inetSocketAddress = new InetSocketAddress(InetAddress.getByName("127.0.0.1"),9999);

        System.out.println(inetSocketAddress.getHostName()); //127.0.0.1(localhost)
        System.out.println(inetSocketAddress.getPort()); //9999

        InetAddress inetAddress = inetSocketAddress.getAddress();
        System.out.println(inetAddress.getHostAddress()); //127.0.0.1 返回地址
        System.out.println(inetAddress.getHostName());   //输入计算机名
    }
}
