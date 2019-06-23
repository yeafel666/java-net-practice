package com.yeafel.net.url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Yeafel
 * 2019/6/23 16:54
 * Do or Die,To be a better man!
 */
public class URLDemo01 {

    public static void main(String[] args) throws MalformedURLException {
        //绝对路径构建
        URL url = new URL("http://www.baidu.com:80/index.html#aa?username=yeafel");
        System.out.println("协议"+url.getProtocol());
        System.out.println("域名"+url.getHost());
        System.out.println("端口"+url.getPort());
        System.out.println("资源"+url.getFile());
        System.out.println("相对路径资源"+url.getPath());
        System.out.println("锚点"+url.getRef());
        System.out.println("参数"+url.getQuery());//?参数：存在锚点返回null，不存在锚点，返回正确
        System.out.println("------------------------以下为不存在锚点的锚点和参数获取");
        URL url2 = new URL("http://www.baidu.com:80/index.html?username=yeafel");
        System.out.println("锚点"+url2.getRef());
        System.out.println("参数"+url2.getQuery());//?参数：存在锚点返回null，不存在锚点，返回正确


    }
}
