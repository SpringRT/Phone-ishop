<%@ page import="ru.phone.ishop.session.UserSession" %>
<%@ page import="ru.phone.ishop.session.SessionManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserSession userSession = SessionManager.getSession(request,response);

%>
