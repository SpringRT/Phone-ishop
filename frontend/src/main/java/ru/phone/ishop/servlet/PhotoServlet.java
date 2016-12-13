package ru.phone.ishop.servlet;


import org.apache.commons.io.FileUtils;
import ru.phone.ishop.manager.ShopManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class PhotoServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long productId = Long.parseLong(req.getParameter("product_id"));
        File photoFile = ShopManager.getPhotoManager().getPhotoFile(productId);
        resp.setContentType("image/png");
        if (photoFile.exists()){
           byte[] imageBytes =  FileUtils.readFileToByteArray(photoFile);
            resp.getOutputStream().write(imageBytes);
        }
    }

}
