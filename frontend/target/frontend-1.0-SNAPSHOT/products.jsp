<%@ page import="ru.phone.ishop.manager.ShopManager" %>
<%@ page import="ru.phone.ishop.session.SessionManager" %>
<%@ page import="ru.phone.ishop.products.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="var.jsp"%>

<%@include file="header.jsp"%>

<%
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
</p>
<p>
    <table border="1" cellpadding="5" cellspacing="0">
    <tr><th>Name</th><th>Description</th><th>Price</th></tr>
    <%
        for(Product product : products){
            %>
    <tr><td> <%=product.getName()%></td><td> <%=product.getShortDescription()%></td><td><%=product.getPrice()%></td></tr>
    <%
        }
    %>
</table>
</p>




<%@include file="footer.jsp"%>


