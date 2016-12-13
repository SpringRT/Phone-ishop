<%@ page import="ru.phone.ishop.manager.ShopManager" %>
<%@ page import="ru.phone.ishop.session.SessionManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="var.jsp"%>
<%
    if (!userSession.isGuest()){
        response.sendRedirect("/");
    }else {
    String login = request.getParameter("login");
    String password = request.getParameter("password");
    boolean rememberMe = request.getParameter("remember_me")!= null;
    boolean error = false;
    User user = null;
    if (login != null){
        user = ShopManager.getUserManager().getUser(login,password);
        if (user != null){
            userSession.setUser(user);
            if (rememberMe){
               userSession.rememberUser();
            }else {
                userSession.discardUser();
            }
            response.sendRedirect("/");
        }else {
            error = true;
        }
    }
    if(user == null){

%>
<%@include file="header.jsp"%>
<%
    if (error) {
%>
<p style="color:red">Incorrect login or password. Please try again.</p>
<%  }

%>

<form id="login-form" action="" method="post" >
    <table >
        <caption align="top"><i>ENTRANCE</i></caption>
        <tr><td>Login</td> <td><input name="login" type="text" value="<%=login != null? login :""%>" /></td></tr>
        <tr><td>Password</td> <td><input name="password" type="password" value="<%=password != null? password :""%>" /></td></tr>
        <a href="index.jsp"> <tr><td>&nbsp;</td> <td><input type="submit" value="Enter"/></td></tr></a>
        <tr><td>&nbsp;</td><td><input type="checkbox"  name="remember_me" value="yes" >remember me</td></tr>
        <tr><td>&nbsp;</td><td><a href="registration.jsp">Registration</a></td></tr>

    </table>

</form>


<%@include file="footer.jsp"%>

<%
    }}
%>