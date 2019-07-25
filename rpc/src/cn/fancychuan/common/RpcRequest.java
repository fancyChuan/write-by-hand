package cn.fancychuan.common;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 用于封装远程调用的信息，比如类、方法、参数
 */
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 8025711078272654028L;

    private String className;
    private String methodName;
    private Object[] params;

    @Override
    public String toString() {
        return "RpcRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", garams=" + Arrays.toString(params) +
                '}';
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
