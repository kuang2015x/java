package rpc;

import sun.java2d.loops.ProcessPath;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * describe:
 *
 * @author kuang
 * @date 2019-11-08 14:22
 */
public class RpcService {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void start(int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                final Socket socket = serverSocket.accept();

                executorService.execute(new ProcessHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
