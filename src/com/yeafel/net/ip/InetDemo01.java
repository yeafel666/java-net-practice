package com.yeafel.net.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Yeafel
 * 没有封装端口：
 * 2019/6/23 17:24
 * Do or Die,To be a better man!
 */
public class InetDemo01 {
    public static void main(String[] args) throws UnknownHostException {
        //使用getLocalhost方法创建InetAddress对象
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostAddress());  //返回192.168.31.60
        System.out.println(inetAddress.getHostName());      //输出计算机名

        //根据域名得到InetAddress对象
        inetAddress = InetAddress.getByName("www.163.com");
        System.out.println(inetAddress.getHostAddress());//返回163得到服务器ip，119.84.128.253
        System.out.println(inetAddress.getHostName());//输出 www.163.com

        //根据ip得到InetAddress对象
        inetAddress = InetAddress.getByName("119.84.128.253");
        System.out.println(inetAddress.getHostAddress());  //返回163服务器的ip: 119.84.128.253
        System.out.println(inetAddress.getHostName());   //如果ip地址存在，并且DNS给你解析，则返回域名www.163.com，如果ip地址不存在或者DNS不给你解析，则返回ip(119.84.128.253)而不是域名

    }
}
