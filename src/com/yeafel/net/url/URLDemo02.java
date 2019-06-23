package com.yeafel.net.url;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Yeafel
 * 获取资源 ： 源代码
 * 2019/6/23 16:54
 * Do or Die,To be a better man!
 */
public class URLDemo02 {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");//主页，默认资源

        //获取资源 网络流
      /*
        InputStream is = url.openStream();
        byte[] flush = new byte[1024];
        int len;
        while (-1 != (len = is.read(flush))) {
            System.out.println(new String(flush,0,len));
        }
        is.close();
        */

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("baidu.html"), "utf-8"));
        String msg;
        while ( (msg=bufferedReader.readLine()) != null) {
//            System.out.println(msg);
            bufferedWriter.append(msg);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();

        bufferedWriter.close();
        bufferedReader.close();
    }
}
