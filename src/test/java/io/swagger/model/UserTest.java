package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
    public void settingIdToValueLowerThanZeroShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setId(-1L));
        assertEquals("Cannot be lower than zero", exception.getMessage());
    }

    @Test
    public void settingDayLimitToValueLowerThanZeroShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setDayLimit(-1L));
        assertEquals("Cannot be lower than zero", exception.getMessage());
    }

    @Test
    public void settingTransactionLimitToValueLowerThanZeroShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setTransactionLimit(-1L));
        assertEquals("Cannot be lower than zero", exception.getMessage());
    }

    @Test
    public void settingEmptyUsernameShouldThrowException() {
        Exception exception = assertThrows(ResponseStatusException.class,
                () -> user.setUsername(" "));
        assertEquals( "422 UNPROCESSABLE_ENTITY \"Username can not be empty\"", exception.getMessage());
    }

    @Test
    public void settingEmailWithoutAtSignShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setEmail("samuel#gmail.com"));
        assertEquals("Email must contain the at sign", exception.getMessage());
    }
}