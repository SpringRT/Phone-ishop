package ru.phone.ishop.servlet;


import ru.phone.ishop.manager.ShopManager;
import ru.phone.ishop.session.SessionManager;
import ru.phone.ishop.session.UserSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProductToBucketServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long productId = Long.parseLong(req.getParameter("product_id"));
        UserSession userSession = SessionManager.getSession(req,resp);
        ShopManager.getBucketManager().addProductToBucket(userSession.getUser().getId(),productId);
        int productsCountInBucket = ShopManager.getBucketManager().getProductsCountInBucket(userSession.getUser().getId());
        resp.getWriter().print(productsCountInBucket);
    }
}
