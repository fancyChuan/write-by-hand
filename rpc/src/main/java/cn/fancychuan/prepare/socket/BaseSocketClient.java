package cn.fancychuan.prepare.socket;

import java.io.*;
import java.net.Socket;

/**
 * 基本的socket客户端
 *
 * 完成发送一个消息，并接收服务端的响应，之后退出
 */
public class BaseSocketClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 1000);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("I am from client \n".getBytes()); // 往服务端发送个消息。
        // 因为服务端是通过BufferedReader获取消息的，而BufferedReader.readLine()是以\n为分隔符的
        // 所以如果上面一行代码不加上\n那么下面一行代码就算flush()了，服务端也一直处于阻塞状态，直到接收到\n。
        outputStream.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(reader.readLine()); // 这个地方是阻塞的。不能在这之前调用 outputStream.close() 这个一调用，socket也断开了

        reader.close();
        outputStream.close();
        socket.close();
    }
}
