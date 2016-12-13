package ru.phone.ishop.photo;


import java.io.File;

public class FileSystemPhotoManager implements PhotoManager {

    public static final String PHOTO_ROOT_FOLDER_PATH = "C:\\Users\\11\\Desktop\\Phone-ishop\\shop-photos";

    @Override
    public File getPhotoFile(long productId) {
        String filename = productId + ".png";
        return new File(PHOTO_ROOT_FOLDER_PATH,filename);
    }

}
