package ru.phone.ishop.products;

import org.junit.Test;
import ru.phone.ishop.AbstractTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SqlProductManagerTest extends AbstractTest {


    @Test
    public void test() {
        int beforeProductCount = productManager.getProductCount();
        Product product = new Product();
        product.setName(salted("name1"));
        product.setShortDescription("short description");
        product.setFullDescription("full description");
        product.setPrice(100);
        product.setBrand("brand");

        long productId = productManager.addProduct(product);
        assertTrue(productId > 0);

        int afterProductCount = productManager.getProductCount();
        assertEquals(beforeProductCount, afterProductCount - 1);

        Product dbProduct = productManager.getProduct(productId);
        assertEquals(productId, dbProduct.getId());
        assertEquals(product.getName(), dbProduct.getName());
        assertEquals(product.getShortDescription(), dbProduct.getShortDescription());
        assertEquals(product.getFullDescription(), dbProduct.getFullDescription());
        assertEquals(product.getPrice(), dbProduct.getPrice());
        assertEquals(product.getBrand(), dbProduct.getBrand());
        assertEquals(product.getCreationTimestamp(), dbProduct.getCreationTimestamp());

        Product product2 = new Product();
        product2.setName(salted("name2"));
        product2.setShortDescription("short description2");
        product2.setFullDescription("full description2");
        product2.setPrice(200);
        product2.setBrand("brand2");
        productManager.addProduct(product2);

        Product product3 = new Product();
        product3.setName(salted("name3"));
        product3.setShortDescription("short description3");
        product3.setFullDescription("full description3");
        product3.setPrice(300);
        product3.setBrand("brand3");
        productManager.addProduct(product3);

        List<Long> productIdList = Arrays.asList(product2.getId(), product3.getId());
        List<Product> products = productManager.listOrderedProductsByCreationTimestamp(productIdList);
        assertEquals(2, products.size());
        assertEquals(product3.getId(), products.get(0).getId());
        assertEquals(product2.getId(), products.get(1).getId());

        products = productManager.listOrderedProductsByCreationTimestamp(2, 1);
        assertEquals(2, products.size());
        assertEquals(product2.getId(), products.get(0).getId());
        assertEquals(product.getId(), products.get(1).getId());

        product.setShortDescription("short description 2");
        productManager.updateProduct(product);
        dbProduct = productManager.getProduct(productId);
        assertEquals("short description 2", dbProduct.getShortDescription());

        productManager.deleteProduct(productId);
        dbProduct = productManager.getProduct(productId);
        assertNull(dbProduct);


    }
}
