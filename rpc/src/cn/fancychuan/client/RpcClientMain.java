package cn.fancychuan.client;

import cn.fancychuan.common.IGpHello;

public class RpcClientMain {


    public static void main(String[] args) {

        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        IGpHello gpHello = rpcClientProxy.clientProxy(IGpHello.class, "localhost", 8080);
        System.out.println(gpHello.sayHello("fancy"));
    }
}
