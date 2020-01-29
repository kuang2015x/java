package bean;

import lombok.Data;

import java.io.Serializable;

/**
 * describe:shdsldjsld
 *
 * @author kuang
 * @date 2019-11-07 18:14
 */
@Data
public class Product implements Serializable {
    private int id;
    private String productName;
    private int num;
}
