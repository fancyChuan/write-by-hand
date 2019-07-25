package cn.fancychuan.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcProxyServer {
    // 开启线程池，用来处理接受到的客户端请求
    ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(Object server, int port) {
        ServerSocket serverSocket = null;

        try {
            // 启动一个服务
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(server, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
