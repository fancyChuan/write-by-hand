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
        // 接收到客户端的socket请求，服务端也对应产生一个socket。这里是单线程的，也就是接受了服务之后就无法接受其他客户端的连接了
        Socket clientSocket = serverSocket.accept();
        // 处理输入数据
        Scanner scanner = new Scanner(clientSocket.getInputStream()); // 获取客户端输入的内容
        scanner.useDelimiter("\n");
        // 使用PrintStream是通过系统标准输入输出流和客户端通信
        PrintWriter ps = new PrintWriter(clientSocket.getOutputStream()); // 往客户端写回消息
        boolean flag = true; //循环标识
        while (flag) {
            if (scanner.hasNext()) {
                String content = scanner.next();
                System.out.println("[from client]" + content);
                if ("exit".equalsIgnoreCase(content)) {
                    ps.println("[server] bye bye ~");
                    flag = false;
                } else {
                    System.out.println("[client] " + content);
                    ps.println("received message: " + content); // 通知客户端已收到消息
                    ps.flush(); // 刷新输出流，立刻写回客户端
                }
            }
        }

        scanner.close();
        ps.close();
        clientSocket.close();
        serverSocket.close();
        System.out.println("closed");
    }
}
