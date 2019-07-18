package cn.fancychuan.prepare.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * socket server
 */
public class EchoSocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1000);
        while (true) {
            // 接收到客户端的socket请求，服务端也对应产生一个socket
            Socket accept = serverSocket.accept();
            InputStream clientInput = accept.getInputStream(); // 获取客户端输入的内容
            OutputStream clientOutput = accept.getOutputStream(); // 往客户端写回消息

            // 使用PrintStream方便往客户端写内容
            PrintWriter ps = new PrintWriter(clientOutput);
            // 处理输入数据
            Scanner scanner = new Scanner(clientInput);
            scanner.useDelimiter("\n");
            boolean flag = true;
            while (flag) {
                if (scanner.hasNext()) {
                    String content = scanner.next();
                    System.out.println("[from client]" + content);
                    if ("exit".equalsIgnoreCase(content)) {
                        ps.println("[server] bye bye ~");
                        flag = false;
                    } else {
                        System.out.println("[client] " + content);
                        ps.println("[server] received message: " + content); // 通知客户端已收到消息
                        ps.flush();
                    }
                }
            }

            accept.close();
            serverSocket.close();
        }
    }
}
