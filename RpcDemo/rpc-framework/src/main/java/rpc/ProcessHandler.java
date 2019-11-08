package rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * describe:
 *
 * @author kuang
 * @date 2019-11-08 14:38
 */
public class ProcessHandler implements Runnable{

    private Socket socket;

    public ProcessHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        //接收客户端请求，解析消息体数据
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();

            Class clazz = null;
            if (Registry.map.containsKey(rpcRequest.getClassName())){
                clazz = Registry.map.get(rpcRequest.getClassName());
            }else {
                throw new RuntimeException("");
            }

            //反射调用本地方法
            Method method = clazz.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
            Object result = method.invoke(clazz.newInstance(), rpcRequest.getParamters());

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                objectInputStream.close();
                objectOutputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
