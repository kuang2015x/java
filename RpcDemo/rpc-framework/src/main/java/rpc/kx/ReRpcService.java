package rpc.kx;
import	java.util.HashMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import rpc.kx.ProcessHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 　　* @description: TODO
 * 　　* @author kx
 * 　　* @date 2020/03/16 18:32
 *
 */
@Component
public class ReRpcService implements ApplicationContextAware, InitializingBean {

    private Map<String, Object> map = new HashMap<String, Object> ();
    private int port;

    public ReRpcService(int port) {
        this.port = port;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

            try {
                ServerSocket serverSocket = new ServerSocket(port);
                while (true){
                    final Socket socket = serverSocket.accept();

                    executorService.execute(new ProcessHandler(socket, (HashMap<String, Object>) map));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String,Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (!beansWithAnnotation.isEmpty()){
            for (Object service : beansWithAnnotation.values()){
                System.out.println(service.getClass().getName());
                RpcService rpcService =service.getClass().getAnnotation(RpcService.class);
                String serviceName = rpcService.value().getName();
                map.put(serviceName,rpcService);
            }
        }
    }
}
