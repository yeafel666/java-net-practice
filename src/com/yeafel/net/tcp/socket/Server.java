package com.yeafel.net.tcp.socket;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Yeafel
 必须先启动服务器后连接
 * 1、创建服务器，指定端口 ServerSocket(int port)
 * 2、接收客户端的连接
 * 3、发送数据+接收数据
 * 2019/6/24 9:28
 * Do or Die,To be a better man!
 */
public class Server {
    public static void main(String[] args) throws IOException {

        //1、创建服务器，指定端口 ServerSocket(int port)
        //可以指定和UDP协议相同端口，不同协议，端口可以相同，不同协议，端口不能相同。
        ServerSocket serverSocket = new ServerSocket(8888);

        //2、接收客户端的连接   阻塞式
        Socket socket = serverSocket.accept();
        System.out.println("一个客户端建立连接");

        //3、发送数据
        String msg = "欢迎使用";
        //输出流
      /*  BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())
        );
        bufferedWriter.write(msg);
        bufferedWriter.newLine(); //要么加这行代码，要么在"欢迎使用"加上\r\n,不然客户端的readLine方法报错。(Connetion reset)
        //不要关闭，否则会被socket管道也关闭
        bufferedWriter.flush();*/

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(msg);
        dataOutputStream.flush();

        //不要关闭，否则会被socket管道也关闭



    }
}
