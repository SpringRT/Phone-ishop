<%@ page import="ru.phone.ishop.manager.ShopManager" %>
<%@ page import="ru.phone.ishop.session.SessionManager" %>
<%@ page import="ru.phone.ishop.products.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="var.jsp"%>

<% if(userSession.isGuest()){
      response.sendRedirect("/");
}else {
    String deletedProductIdStr = request.getParameter("deleted_product_id");
    if (deletedProductIdStr != null){
        long deletedProductId = Long.parseLong(deletedProductIdStr);
        ShopManager.getBucketManager().deleteProductFromBucket(userSession.getUser().getId(),deletedProductId );
    }
    List<Long> bucketProductIdList = ShopManager.getBucketManager().listProductsFromBucket(userSession.getUser().getId());
    List<Product> bucketProducts = ShopManager.getProductManager().listOrderedProductsByCreationTimestamp(bucketProductIdList);
    int priceTotal = 0;
    for (Product product : bucketProducts){
        priceTotal += product.getPrice();
    }
    boolean bought = false;
    if (request.getParameter("buy") != null) {
        ShopManager.getBucketManager().payOrder(userSession.getUser().getId());
        bought = true;
    }
%>
<%@include file="header.jsp"%>
<h2>Bucket</h2>
<%
    if (userSession.isGuest()){
      %>
<script type="text/javascript">
    location = "/";
</script>
  <%  } else if (bought) {
%>
<p style="color: green" >
    You bought products for <b><%=priceTotal%></b> total price.
</p>
<%
    }else {
%>
<p>
    Products count <b><%=bucketProducts.size()%></b> for <b><%=priceTotal%></b> total price.
</p>

<p>
<table border="1" cellpadding="5" cellspacing="0">
    <tr><th>Name</th><th>Description</th><th>Brand</th><th>Photo</th><th>Price</th></tr>

    <%
        for(Product product : bucketProducts){
    %>

    <tr><td> <%=product.getName()%></td>
        <td> <%=product.getShortDescription()%></td>
        <td><%=product.getBrand()%></td>
        <td></td>
        <td><%=product.getPrice()%></td>
        <td>
            <form action="" method="post">
            <input type="hidden" name="deleted_product_id" value="<%=product.getId()%>">
            <input type="submit" value="Delete" />
           </form>
        </td>
    </tr>

    <%
        }
    %>
</table>
</p>
<%
   if (!bucketProducts.isEmpty()){
%>
<p>
      <form action="" method="post">
      <input type="submit" name="buy" value="Buy" />
      </form>
</p>
<% }} %>

<%@include file="footer.jsp"%>


<% } %>