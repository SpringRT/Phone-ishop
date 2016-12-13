package ru.phone.ishop.products;

import java.util.List;

public interface ProductManager {

    long addProduct(Product product);

    Product getProduct(long productId);

    void updateProduct(Product product);

    void deleteProduct(long productId);

    int getProductCount();

    List<Product> listOrderedProductsByCreationTimestamp(int count, int offset);

    List<Product> listOrderedProductsByCreationTimestamp(List<Long> productIdList);


}
