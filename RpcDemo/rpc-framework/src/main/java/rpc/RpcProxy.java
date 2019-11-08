package rpc;


import java.lang.reflect.Proxy;

/**
 * describe:
 *
 * @author kuang
 * @date 2019-11-08 16:30
 */
public class RpcProxy<T> {
    public T remoteCall(Class<T> interfaceClass, String host, int port) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class[]{interfaceClass},new RemoteInvocateionHandler(interfaceClass.getName(),host,port));
    }
}
