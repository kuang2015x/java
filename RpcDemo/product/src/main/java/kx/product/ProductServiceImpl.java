package kx.product;

import bean.Product;
import service.ProductService;

/**
 * describe:
 *
 * @author kuang
 * @date 2019-11-08 14:13
 */
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getById(int id) {

        Product product = new Product();
        product.setId(id);
        product.setProductName("产品一");
        product.setNum(5);
        return product;
    }
}
