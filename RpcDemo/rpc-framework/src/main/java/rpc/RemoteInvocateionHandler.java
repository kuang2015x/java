package rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * describe:
 *
 * @author kuang
 * @date 2019-11-08 16:43
 */
public class RemoteInvocateionHandler implements InvocationHandler {

    private String className;
    private String host;
    private int port;

    public RemoteInvocateionHandler(String className, String host, int port) {
        this.className = className;
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //获取连接
        Socket socket = new Socket(host,port);
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(className);
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParamters(args);
        rpcRequest.setTypes(method.getParameterTypes());

        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;

        Object result = null;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            objectInputStream = new ObjectInputStream(socket.getInputStream());
             result = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        }

        return result;
    }
}
