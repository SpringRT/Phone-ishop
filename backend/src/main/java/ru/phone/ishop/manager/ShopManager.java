package ru.phone.ishop.manager;

import ru.phone.ishop.bucket.BucketManager;
import ru.phone.ishop.bucket.SqlBucketManager;
import ru.phone.ishop.message.MessageManager;
import ru.phone.ishop.message.SqlMessageManager;
import ru.phone.ishop.photo.FileSystemPhotoManager;
import ru.phone.ishop.photo.PhotoManager;
import ru.phone.ishop.products.ProductManager;
import ru.phone.ishop.products.SqlProductManager;
import ru.phone.ishop.users.SqlUserManager;
import ru.phone.ishop.users.UserManager;

public class ShopManager {
    private static UserManager userManager = new SqlUserManager();
    private static ProductManager productManager = new SqlProductManager();
    private static BucketManager bucketManager = new SqlBucketManager();
    private static PhotoManager photoManager = new FileSystemPhotoManager();
    private static MessageManager messageManager = new SqlMessageManager();

    public static MessageManager getMessageManager() {
        return messageManager;
    }

    public static UserManager getUserManager() {
        return userManager;
    }

    public static ProductManager getProductManager() {
        return productManager;
    }

    public static BucketManager getBucketManager() {
        return bucketManager;
    }

    public static PhotoManager getPhotoManager() {
        return photoManager;
    }


}
