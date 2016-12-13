package ru.phone.ishop.session;

import ru.phone.ishop.manager.ShopManager;
import ru.phone.ishop.users.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class UserSession {
    private User user;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public void setRequestResponse(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public boolean isGuest() {
        return user == null;
    }

    public boolean isAdmin() {
        return user != null && user.isAdmin();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void rememberUser() {
        Cookie login = getCookie("login");
        Cookie password = getCookie("password");
        login.setValue(user.getLogin());
        password.setValue(user.getPassword());
        login.setMaxAge((int)TimeUnit.DAYS.toSeconds(365));
        password.setMaxAge((int)TimeUnit.DAYS.toSeconds(365));
        response.addCookie(login);
        response.addCookie(password);
    }

    public void discardUser() {
        Cookie login = getCookie("login");
        Cookie password = getCookie("password");
        login.setValue(null);
        password.setValue(null);
        login.setMaxAge(0);
        password.setMaxAge(0);
        response.addCookie(login);
        response.addCookie(password);
    }

    public void autoLogin() {
        String login = getCookie("login").getValue();
        String password = getCookie("password").getValue();
        if (login != null && password != null) {
            user = ShopManager.getUserManager().getUser(login, password);
        }
    }

    private Cookie getCookie(String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        return cookie;
    }
}
