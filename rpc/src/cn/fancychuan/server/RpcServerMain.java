package cn.fancychuan.server;

import cn.fancychuan.common.IGpHello;

public class RpcServerMain {

    public static void main(String[] args) {
        IGpHello gpHello = new GpHelloImpl();
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publisher(gpHello, 8080);  // 把服务发布到8080端口
    }
}
