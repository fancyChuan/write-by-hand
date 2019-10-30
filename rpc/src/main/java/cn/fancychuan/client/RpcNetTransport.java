package cn.fancychuan.client;

import cn.fancychuan.common.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 网络传输过程
 */
public class RpcNetTransport {

    String host;
    int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket() {
        System.out.println("begin create socket connet");

        Socket socket = null;
        try {
            socket = new Socket(host, port);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("build connect failed");
        }
        return socket;
    }

    public Object send(RpcRequest request) {
        Object result = null;

        try {
            Socket socket = newSocket();
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request); // 也是一个序列化机制，把对象写成流
            outputStream.flush();
            // 接受服务端返回的内容
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            result = inputStream.readObject(); // 反序列化得到结果

            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
