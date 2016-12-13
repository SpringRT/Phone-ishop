<%@ page import="ru.phone.ishop.manager.ShopManager" %>
<%@ page import="ru.phone.ishop.session.SessionManager" %>
<%@ page import="ru.phone.ishop.products.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.phone.ishop.product.ProductEditor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="var.jsp"%>

<%@include file="header.jsp"%>

<%
    ProductEditor.EditParameters editParameters = ProductEditor.getEditParameters(request);
    boolean added = false;
    Product product = new Product();
    if (editParameters != null){
        String productName = editParameters.getProp("product_name");
        product.setName(productName);
        product.setShortDescription(editParameters.getProp("product_short_description"));
        product.setFullDescription(editParameters.getProp("product_full_description"));
        product.setPrice(Integer.parseInt(editParameters.getProp("product_price")));
        product.setBrand(editParameters.getProp("product_brand"));
        long productId = ShopManager.getProductManager().addProduct(product);
        ProductEditor.uploadPhoto(productId,editParameters.getPhotoItem());
        added = true;
    }
%>
<form action="" method="post" enctype="multipart/form-data">
    <h2>Add Product</h2>
    <% if (added){ %>
        <p style="color: green">
            Product (id = <a href="product.jsp?product_id=<%=product.getId()%>"><%=product.getId()%></a>)is added successfully!
        </p>
    <%} else {%>

    <table style="border: 1px solid #aaaaaa" cellpadding="10" cellspacing="0">
        <tr><td>Name</td><td><input type="text" name="product_name" value=""/></td></tr>
        <tr><td>Short description</td><td><textarea name="product_short_description"></textarea></td></tr>
        <tr><td>Full description</td><td><textarea name="product_full_description"></textarea></td></tr>
        <tr><td>Price</td><td><input type="text" name="product_price" value=""/></td></tr>
        <tr><td>Brand</td><td><input type="text" name="product_brand" value=""/></td></tr>
        <tr><td>Photo</td><td><input type="file" name="photo" size="50" /></td></tr>
        <tr><td>&nbsp;</td><td><input type="submit" value="Add"/></td></tr>

    </table>
   <% } %>
</form>

<%@include file="footer.jsp"%>


