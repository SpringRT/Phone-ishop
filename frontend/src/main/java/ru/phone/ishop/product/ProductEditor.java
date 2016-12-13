package ru.phone.ishop.product;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.phone.ishop.manager.ShopManager;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProductEditor {
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 10;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10;


    public static EditParameters getEditParameters(HttpServletRequest request) {

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        EditParameters editParameters = null;
        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MAX_MEMORY_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(MAX_REQUEST_SIZE);
            try {
                editParameters = new EditParameters();
                List photoItems = upload.parseRequest(request);
                Iterator<FileItem> it = photoItems.iterator();
                while (it.hasNext()) {
                    FileItem item = it.next();
                    if (item.isFormField()) {
                        editParameters.productProps.put(item.getFieldName(), item.getString());
                    } else {
                        editParameters.photoItem = item;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Error in getting edit parameters ");
            }
        }
        return editParameters;
    }

    public static void uploadPhoto(long productId, FileItem photoItem) {
        try {
            if (!photoItem.getName().isEmpty()){
                File photoFile = ShopManager.getPhotoManager().getPhotoFile(productId);
                photoItem.write(photoFile);
                System.out.println("Photo is uploaded for product: " + productId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Photo is not uploaded for product: " + productId, e);
        }
    }

    public static class EditParameters {
        private Map<String, String> productProps = new HashMap<>();
        private FileItem photoItem;

        public Map<String, String> getProductProps() {
            return productProps;
        }
        public String getProp(String name){
            return productProps.get(name);
        }

        public FileItem getPhotoItem() {
            return photoItem;
        }
    }
}
