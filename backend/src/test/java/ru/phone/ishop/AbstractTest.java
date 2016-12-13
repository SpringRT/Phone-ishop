package ru.phone.ishop;

import ru.phone.ishop.bucket.BucketManager;
import ru.phone.ishop.bucket.SqlBucketManager;
import ru.phone.ishop.products.ProductManager;
import ru.phone.ishop.products.SqlProductManager;
import ru.phone.ishop.users.SqlUserManager;
import ru.phone.ishop.users.UserManager;

public abstract class AbstractTest {
    private long testId = System.currentTimeMillis();
    protected BucketManager bucketManager = new SqlBucketManager();
    protected ProductManager productManager = new SqlProductManager();
    protected UserManager userManager = new SqlUserManager();

    protected String salted(String str) {
        return str + "-" + testId;
    }

}
