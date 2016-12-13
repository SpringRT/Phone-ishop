<%@ page import="ru.phone.ishop.manager.ShopManager" %>
<%@ page import="ru.phone.ishop.session.SessionManager" %>
<%@ page import="ru.phone.ishop.products.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.phone.ishop.message.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="var.jsp"%>
<%
    long productId = Long.parseLong(request.getParameter("product_id"));
    Product product = ShopManager.getProductManager().getProduct(productId);
    String addedMessage = request.getParameter("message");
    if(addedMessage != null && !userSession.isGuest()){
        Message message = new Message();
        message.setProductId(productId);
        message.setUserId(userSession.getUser().getId());
        message.setMessage(addedMessage);
        ShopManager.getMessageManager().addMessage(message);
    }
    List<Message> messages = ShopManager.getMessageManager().listMessages(productId);
%>
<%@include file="header.jsp"%>

<h2><%=product.getName()%></h2>
<p>
    <span>Brand: </span><span><%=product.getBrand()%></span>
    <br/>
    <span>Price: </span><span><%=product.getPrice()%></span>
</p>
<p>
    <img src="/product_photo?product_id=<%=product.getId()%>"/>
</p>
<p>
    <b>Description</b>
    <br/>
    <span><%=product.getFullDescription()%></span>

</p>
<p>
    <b>Comments (<%=messages.size()%>)</b>
    <%
        if (!userSession.isGuest()){%>
    <form action="?product_id=<%=productId%>" method="post" >
        <textarea name="message"></textarea>
        <input type="submit" value="Add comment"/>
    </form>
    <%
        }
    %>
    <% for (Message message:messages){%>
    <span><%=message.getUserName()%></span><br/>
    <span><%=message.getMessage()%></span><br/><br/>


    <% } %>

</p>

<%@include file="footer.jsp"%>


