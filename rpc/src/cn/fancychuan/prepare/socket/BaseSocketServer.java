package cn.fancychuan.prepare.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基本socket服务端
 *
 * 这是一个常驻进程服务，但是一次只能服务一次连接，是阻塞的。并且需要完成一次 "读消息、写消息“” 才算交互完成，才会服务下一个连接
 */
public class BaseSocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1000);
        while (true) { // 一直处于服务状态
            Socket clientSocket = server.accept(); // 监听到有客户端连接，这个地方是阻塞的，函数执行到这里会不断监听，直到有客户端连接过来
            System.out.println("接收到连接请求");
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String content = reader.readLine(); // readLine也是自带阻塞的，而且要注意接收到的信息一定要带有换行符\n，否则会一直阻塞。
            System.out.println(content);
            // 通知客户端已收到信息
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write("received \n".getBytes());
            // 关闭输入输出流
            reader.close();
            outputStream.close();
        }
    }
}
