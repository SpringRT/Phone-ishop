<%@ page import="ru.phone.ishop.users.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserSession headerUserSession = userSession;
    if(request.getParameter("logout")!= null){
        headerUserSession.setUser(null);
        headerUserSession.discardUser();
    }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>phone-ishop</title>
    <script type="text/javascript" language="JavaScript" src="/js/shop.js"></script>

</head>
<body background="https://ilyakhasanov.ru/images/background/27.jpg">
<p>
   <table width="100%" >
    <tr>

        <td><a href="/"><i>Phone-Ishop.ru</i></a></td>
        <td align="right">
            <% if (headerUserSession.isGuest()){%>
            <a href="login.jsp">Sign in</a>

            <% } else { %>
            <%=headerUserSession.getUser().getLogin()%> |
            <a href="bucket.jsp">Bucket (<span id="bucket_size_id"><%=ShopManager.getBucketManager().getProductsCountInBucket(headerUserSession.getUser().getId())%></span>)</a> |
            <a href="?logout">Log out</a>


            <% } %>

        </td>

    </tr>
</table>
<hr/>
</p>


