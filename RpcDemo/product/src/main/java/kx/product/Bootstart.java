package kx.product;

import rpc.Registry;
import rpc.RpcService;

/**
 * describe:
 *
 * @author kuang
 * @date 2019-11-08 16:24
 */
public class Bootstart {
    public static void main(String[] args) {
        Registry.map.put("service.ProductService", ProductServiceImpl.class);
        RpcService rpcService = new RpcService();
        rpcService.start(10000);
    }
}
