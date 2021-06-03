package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {


    private User user;
    @BeforeEach

    public void Setup() {
        user = new User();
    }

    @Test
    public void createUserShouldNotBeNull() {
        assertNotNull(user);
    }

    @Test
    public void settingEmptyUsernameShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setUsername(null));
        assertEquals("Username can not be empty", exception.getMessage());
    }


}