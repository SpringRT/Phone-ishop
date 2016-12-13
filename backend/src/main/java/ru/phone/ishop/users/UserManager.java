package ru.phone.ishop.users;

public interface UserManager {

    long addUser(User user);

    boolean existsLogin(String login);

    boolean existsEmail(String email);

    User getUser(String login, String password);

    User getUser(long userId);

    default boolean validateEmail(String email){
        String regexp = "^.+@.+\\..+$";
        return email.matches(regexp);

    }


}
