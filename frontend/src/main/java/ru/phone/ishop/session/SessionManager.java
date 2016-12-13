package ru.phone.ishop.session;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionManager {
    private static final String SESSION_ATTRIBUTE = "phone-ishop-session";

    public static UserSession getSession(HttpServletRequest request, HttpServletResponse response){
        UserSession userSession = (UserSession) request.getSession().getAttribute(SESSION_ATTRIBUTE);
        if(userSession == null){
            userSession = new UserSession();
            request.getSession().setAttribute(SESSION_ATTRIBUTE, userSession);
        }
        userSession.setRequestResponse(request, response);
        if (userSession.isGuest()){
            userSession.autoLogin();
        }
        return userSession;
    }

}
