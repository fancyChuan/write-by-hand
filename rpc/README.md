## 手写RPC框架

### 0. 预备知识
- 网络编程：使用ServerSocket和Socket，参见：[socket](https://github.com/fancychuan/)
- 反射：
- 动态代理java.lang.reflect.Proxy
- 

### 1.设计客户端
#### 1.1 生成代理对象
动态代理的方式：JDK反射、javaassist、ASM、cglib，这些都是字节码生成器




核心：动态代理 + 反射 + socket（netty） + 序列化（可配置）

RPC框架有：webservice/dubbo/grpc/thrift/hsf/motan/rmi/ice



