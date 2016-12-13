<%@ page import="ru.phone.ishop.manager.ShopManager" %>
<%@ page import="ru.phone.ishop.session.SessionManager" %>
<%@ page import="ru.phone.ishop.products.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.phone.ishop.product.ProductEditor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="var.jsp" %>

<%@include file="header.jsp" %>

<%
    ProductEditor.EditParameters editParameters = ProductEditor.getEditParameters(request);
    long productId;
    Product product;
    boolean updated = false;
    if (editParameters != null) {
        productId = Long.parseLong(editParameters.getProp("product_id"));
        product = ShopManager.getProductManager().getProduct(productId);
        product.setName(editParameters.getProp("product_name"));
        product.setShortDescription(editParameters.getProp("product_short_description"));
        product.setFullDescription(editParameters.getProp("product_full_description"));
        product.setPrice(Integer.parseInt(editParameters.getProp("product_price")));
        product.setBrand(editParameters.getProp("product_brand"));
        ShopManager.getProductManager().updateProduct(product);
        ProductEditor.uploadPhoto(productId,editParameters.getPhotoItem());
        updated = true;
    } else {
        productId = Long.parseLong(request.getParameter("product_id"));
        product = ShopManager.getProductManager().getProduct(productId);
    }

%>
<form action="" method="post" enctype="multipart/form-data">
    <input type="hidden" name="product_id" value="<%=productId%>">
    <h2>Edit Product</h2>
    <% if (updated) { %>
    <p style="color: green">
        Product (id = <a href="product.jsp?product_id=<%=product.getId()%>"><%=product.getId()%>
    </a>) is updated successfully!
    </p>
    <%} %>

    <table style="border: 1px solid #aaaaaa" cellpadding="10" cellspacing="0">
        <tr>
            <td>Name</td>
            <td><input type="text" name="product_name" value="<%=product.getName()%>"/></td>
        </tr>
        <tr>
            <td>Short description</td>
            <td><textarea name="product_short_description"><%=product.getShortDescription()%></textarea></td>
        </tr>
        <tr>
            <td>Full description</td>
            <td><textarea name="product_full_description"><%=product.getFullDescription()%></textarea></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input type="text" name="product_price" value="<%=product.getPrice()%>"/></td>
        </tr>
        <tr>
            <td>Brand</td>
            <td><input type="text" name="product_brand" value="<%=product.getBrand()%>"/></td>
        </tr>
        <tr>
            <td>Photo</td>
            <td><input type="file" name="photo" size="50"/></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="Update"/></td>
        </tr>

    </table>
</form>

<%@include file="footer.jsp" %>


