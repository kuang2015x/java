package rpc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * describe: 注册中心
 *
 * @author kuang
 * @date 2019-11-08 15:01
 */
public class Registry {
    public static ConcurrentHashMap<String,Class> map ;
    static {
        map  = new ConcurrentHashMap<>();
    }
}
