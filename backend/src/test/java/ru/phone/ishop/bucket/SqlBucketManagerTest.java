package ru.phone.ishop.bucket;

import org.junit.Test;
import ru.phone.ishop.AbstractTest;
import ru.phone.ishop.products.Product;
import ru.phone.ishop.users.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SqlBucketManagerTest extends AbstractTest {


    @Test
    public void test() {
        long userId = addUser();
        List<Long> productIdList = addProducts();
        bucketManager.addProductToBucket(userId, productIdList.get(0));
        bucketManager.addProductToBucket(userId, productIdList.get(3));

        List<Long> bucketProductIdList = bucketManager.listProductsFromBucket(userId);
        assertEquals(2, bucketProductIdList.size());
        assertEquals(productIdList.get(0), bucketProductIdList.get(0));
        assertEquals(productIdList.get(3), bucketProductIdList.get(1));

        int productsCount = bucketManager.getProductsCountInBucket(userId);
        assertEquals(2, productsCount);

        bucketManager.deleteProductFromBucket(userId, productIdList.get(0));
        productsCount = bucketManager.getProductsCountInBucket(userId);
        assertEquals(1, productsCount);
        bucketProductIdList = bucketManager.listProductsFromBucket(userId);
        assertEquals(1, bucketProductIdList.size());
        assertEquals(productIdList.get(3), bucketProductIdList.get(0));

        bucketManager.payOrder(userId);
        productsCount = bucketManager.getProductsCountInBucket(userId);
        assertEquals(0, productsCount);


    }

    private long addUser() {
        User user = new User();
        user.setLogin(salted("login-BT"));
        user.setPassword("password1234_BT");
        user.setEmail(salted("email-BT"));
        user.setAdmin(false);
        return userManager.addUser(user);
    }

    private List<Long> addProducts() {
        List<Long> productIdList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setName(salted("name-BT-" + i));
            product.setShortDescription("short description " + i);
            product.setFullDescription("full description" + i);
            product.setPrice(100 + i);
            product.setBrand("brand" + i);

            long productId = productManager.addProduct(product);
            productIdList.add(productId);
        }
        return productIdList;
    }

}
