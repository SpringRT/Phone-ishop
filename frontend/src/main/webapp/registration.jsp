<%@ page import="ru.phone.ishop.manager.ShopManager" %>
<%@ page import="ru.phone.ishop.session.SessionManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="var.jsp"%>
<%
    String login = request.getParameter("login");
    String password = request.getParameter("password");
    String repeatPassword = request.getParameter("repeatPassword");
    String email = request.getParameter("email");
    boolean loginError = false;
    boolean repeatPasswordError = false;
    boolean emailExistsError = false;
    boolean emailInvalidError = false;
    boolean error = false;
    User user = null;
    if (login != null) {
        if (ShopManager.getUserManager().existsLogin(login)){
            loginError = true;
        }
        if (!password.equals(repeatPassword)){
            repeatPasswordError = true;
        }
        if (ShopManager.getUserManager().existsEmail(email)){
            emailExistsError = true;
        }
        if(!ShopManager.getUserManager().validateEmail(email)){
            emailInvalidError = true;
        }
        error = loginError || repeatPasswordError || emailExistsError || emailInvalidError;
        if (!error){
            user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            ShopManager.getUserManager().addUser(user);
            userSession.setUser(user);
            response.sendRedirect("/");
        }

    }

    if (error || login == null){



%>

<%@include file="header.jsp"%>

<form id="login-form" action="" method="post" >
    <table >
        <caption align="top">REGISTRATION</caption>
        <tr><td>Login</td>
            <td>
                <input name="login" type="text" value="<%=login != null? login :""%>" onkeyup="changeLoginSize(this)" onmouseenter="changeLoginSize(this)" onclick="changeLoginSize(this)" onchange="changeLoginSize(this)" onautocomplete="changeLoginSize(this)"/>
                (<span id="login_size_id">0</span> / 10)
            </td><td style="color:red"><%=loginError ? "Login already exists!":"&nbsp;"%></td></tr>
        <tr><td>Password</td> <td><input name="password" type="password" value="<%=password != null? password :""%>" /></td></tr>
        <tr><td>Repeat Password</td> <td><input name="repeatPassword" type="password" value="<%=repeatPassword != null? repeatPassword :""%>" /></td><td style="color:red"><%=repeatPasswordError ? "Not equal to password!":"&nbsp;"%></td></tr>
        <tr><td>Email</td> <td><input name="email" type="text" value="<%=email != null? email :""%>" /></td>
            <td style="color:red"><%=emailExistsError ? "Email already exists!": emailInvalidError? "Email is incorrect!" : "&nbsp;"%></td></tr>
        <tr><td>&nbsp;</td> <td><input type="submit" value="Registrate" /></td></tr>

    </table>


</form>

<%@include file="footer.jsp"%>

<%
    }
%>
