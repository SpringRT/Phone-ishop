package ru.phone.ishop.photo;


import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class FileSystemPhotoManageTest {
    private PhotoManager photoManager = new FileSystemPhotoManager();

    @Test
    public void getPhotoFile(){
        File file =  photoManager.getPhotoFile(100);
        assertEquals("100.png", file.getName());
        assertEquals(FileSystemPhotoManager.PHOTO_ROOT_FOLDER_PATH, file.getParent());
    }
}
