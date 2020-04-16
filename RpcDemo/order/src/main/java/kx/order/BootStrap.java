package kx.order;

import bean.Product;
import rpc.RpcProxy;
import service.ProductService;

/**
 * describe:jhkjgjgjhkj
 *
 * @author kuang
 * @date 2019-11-08 16:34
 */
public class BootStrap {
    public static void main(String[] args) {
        RpcProxy rpcProxy = new RpcProxy();

       ProductService product = (ProductService) rpcProxy.remoteCall(ProductService.class,"localhost",10000);
        System.out.println(product.getById(2));

        System.out.println("调用结束");
        System.out.println("develop 001");
        System.out.println("dev 002");
        System.out.println("dev 004");
        System.out.println("dev 005");
    }
}
