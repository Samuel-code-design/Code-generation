package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    @BeforeEach

    public void Setup() {
        user = new User();

//        List<Role> roles = new ArrayList<>();
//        roles.add(Role.ROLE_EMPLOYEE);
//
//        User u = new User("JD0001", "Wachtwoord1#", "Samuel", "brouwer", "samuel11hoi@gmail.com", "06 12345678", roles, false
//                , 1000L, 1000L);
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

//    @Test
//    public void settingPhoneWithNonNumericalValuesShouldThrowException() {
//        Exception exception = assertThrows(IllegalArgumentException.class,
//                () -> user.setPhone("06 a1234567"));
//        assertEquals("Second part must consist of numbers only", exception.getMessage());
//    }

    @Test
    public void settingEmptyUsernameShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setUsername(null));
        assertEquals("Username can not be empty", exception.getMessage());
    }
    @Test
    public void settingEmailWithoutAtSignShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setEmail("samuel#gmail.com"));
        assertEquals("Email must contain the at sign", exception.getMessage());
    }
}