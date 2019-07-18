package cn.fancychuan.prepare.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoSocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1000);
        PrintStream serverPs = new PrintStream(socket.getOutputStream());  // 从服务端接收到的消息
        Scanner inputScanner = new Scanner(new InputStreamReader(System.in)); // 扫描用户键盘输入
        inputScanner.useDelimiter("\n");
        boolean flag = true;
        while (flag) {
            if (inputScanner.hasNext()) {
                String content = inputScanner.next();
                System.out.println("[client input]" + content);
                serverPs.println(content);
                serverPs.flush();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String fromServer = br.readLine(); // BufferedReader是一个缓冲流，会根据"\n"字符切分为多行，这里默认就取第一行
                System.out.println("[server]" + fromServer);
                if (fromServer.contains("bye bye")) {
                    flag = false;
                }
            }
        }
        serverPs.close();
        inputScanner.close();
        socket.close();
        System.out.println("closed");
    }
}
