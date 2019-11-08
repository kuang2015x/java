package rpc;

import lombok.Data;

import java.io.Serializable;

/**
 * describe:
 *
 * @author kuang
 * @date 2019-11-08 14:42
 */
@Data
public class RpcRequest implements Serializable {
    private String className;
    private String methodName;
    private Object[] paramters;
    private Class[] types;
}
