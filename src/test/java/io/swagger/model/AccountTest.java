package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private Account account;
    @BeforeEach

    public void Setup() {
        account = new Account();
    }

    @Test
    public void createAccountShouldNotBeNull(){
        assertNotNull(account);
    }

    @Test
    public void settingEmptyUserShouldThrowException(){
        Exception exception = assertThrows(IllegalArgumentException.class,
                 () -> account.setUser(null));
        assertEquals("User cannot be empty", exception.getMessage());
    }

    @Test
    public void settingEmptyAbsoluteLimitShouldThrowException(){
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> account.setAbsoluteLimit(null));
        assertEquals("Absolutelimit cannot be empty", exception.getMessage());
    }

    @Test
    public void settingEmptyIbanShouldThrowException(){
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> account.setIban(null));
        assertEquals("Iban cannot be empty", exception.getMessage());
    }

    @Test
    public void settingIncorrectFormatForIbanShouldThrowException(){
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> account.setIban("NL01ABNA0123456789"));
        assertEquals("Iban is not in correct format", exception.getMessage());
    }

    @Test
    public void settingEmptyTypeShouldThrowException(){
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> account.setType(null));
        assertEquals("Account type cannot be empty", exception.getMessage());
    }
}
