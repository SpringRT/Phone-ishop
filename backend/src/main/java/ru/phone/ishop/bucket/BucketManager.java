package ru.phone.ishop.bucket;

import java.util.List;

public interface BucketManager {

    boolean addProductToBucket(long userId, long productId);

    boolean deleteProductFromBucket(long userId, long productId);

    int getProductsCountInBucket(long userId);

    List<Long> listProductsFromBucket(long userId);

    boolean payOrder(long userId);


}
