<%@ page import="ru.phone.ishop.manager.ShopManager" %>
<%@ page import="ru.phone.ishop.session.SessionManager" %>
<%@ page import="ru.phone.ishop.products.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="var.jsp"%>

<%@include file="header.jsp"%>

<%
    String deletedProductIdStr = request.getParameter("deleted_product_id");
    if (deletedProductIdStr != null){
        long deletedProductId = Long.parseLong(deletedProductIdStr);
        ShopManager.getProductManager().deleteProduct(deletedProductId);
    }
    int productsCount = ShopManager.getProductManager().getProductCount();
    final int productsCountOnPage = 10;
    int pagesCount = (productsCount / productsCountOnPage) + 1;
    String str = request.getParameter("page");
    int pageNumber = str!= null ? Integer.parseInt(str) : 1;
    int offset = pagesCount * (pageNumber-1);
    List<Product> products = ShopManager.getProductManager().listOrderedProductsByCreationTimestamp(productsCountOnPage, offset );
%>

<p>Page:
    <% if (pageNumber > 1){%>  <a href="?page=<%=pageNumber-1%>">&laquo;</a> <%}%>
    <%=pageNumber%> of <%=pagesCount%>
    <% if (pageNumber < pagesCount){%>  <a href="?page=<%=pageNumber+1%>">&raquo;</a> <%}%>
    <br/>
    Products count: <%=productsCount%>
</p>
<%
    if(userSession.isAdmin()){
%>
<p>
    <a href="add_product.jsp">Add product</a>
</p>
<% } %>
<p>
    <table align="center" border="1" cellpadding="5" cellspacing="0">
    <tr><th>Name</th><th>Description</th><th>Brand</th><th>Photo</th><th>Price</th></tr>

    <%
        for(Product product : products){
    %>

    <tr><td><a href="product.jsp?product_id=<%=product.getId()%>"> <%=product.getName()%></a></td>
        <td> <%=product.getShortDescription()%></td>
        <td><%=product.getBrand()%></td>
        <td><img src="/product_photo?product_id=<%=product.getId()%>"/></td>
        <td><%=product.getPrice()%></td>
        <% if (!userSession.isGuest()){ %>

        <td>
            <a href="javascript:addProductToBucket(<%=product.getId()%>)">Add to bucket</a>
        </td>
        <% } %>
            <%
                if(userSession.isAdmin()){
            %>
        <td>
            <form action="edit_product.jsp" method="get">
                <input type="hidden" name="product_id" value="<%=product.getId()%>">
                <input type="submit" value="Edit" />

            </form>
        <td>
            <script type="text/javascript">
                function confirmDelete(){
                    return confirm("Are you sure that you want to delete?");
                }
            </script>
            <form action="" method="post" onsubmit="return confirmDelete();">
                <input type="hidden" name="deleted_product_id" value="<%=product.getId()%>">
                <input type="submit" value="Delete" />
            </form>

        </td>
        <% } %>
    </tr>

    <%
        }
    %>

</table>
</p>

<%@include file="footer.jsp"%>


