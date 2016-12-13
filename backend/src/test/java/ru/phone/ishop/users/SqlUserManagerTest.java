package ru.phone.ishop.users;

import org.junit.Test;
import ru.phone.ishop.AbstractTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SqlUserManagerTest extends AbstractTest {


    @Test
    public void test(){
        User user = new User();
        user.setLogin(salted("login"));
        user.setPassword("password1234");
        user.setEmail(salted("email"));
        user.setAdmin(false);
        long userId = userManager.addUser(user);
        assertTrue(userId > 0);

        User dbUser = userManager.getUser(userId);
        assertEquals(userId, dbUser.getId());
        assertEquals(user.getLogin(), dbUser.getLogin());
        assertEquals(user.getPassword(), dbUser.getPassword());
        assertEquals(user.getEmail(), dbUser.getEmail());
        assertEquals(user.isAdmin(), dbUser.isAdmin());

        dbUser = userManager.getUser(user.getLogin(), user.getPassword());
        assertEquals(userId, dbUser.getId());
        assertEquals(user.getLogin(), dbUser.getLogin());
        assertEquals(user.getPassword(), dbUser.getPassword());
        assertEquals(user.getEmail(), dbUser.getEmail());
        assertEquals(user.isAdmin(), dbUser.isAdmin());

        boolean exists = userManager.existsLogin(user.getLogin());
        assertTrue(exists);
        exists = userManager.existsLogin("notExistLogin");
        assertFalse(exists);

        exists = userManager.existsEmail(user.getEmail());
        assertTrue(exists);
        exists = userManager.existsEmail("notExistEmail");
        assertFalse(exists);

    }
    @Test
    public void validateEmail(){
        assertFalse(userManager.validateEmail("1"));
        assertFalse(userManager.validateEmail("frhh@mail"));
        assertTrue(userManager.validateEmail("frhh@mail.ru"));
    }

}
