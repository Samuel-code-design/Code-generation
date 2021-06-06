package io.swagger.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginDTOTest {
    private LoginDTO loginDTO;

    @BeforeEach
    public void Setup() {
        loginDTO = new LoginDTO("JD0001", "Wachtwoord1#");
    }

    @Test
    public void loginUserShouldNotBeNull() {
        assertNotNull(loginDTO);
    }

    @Test
    public void settingUserNameEmptyShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> loginDTO.setUsername(" "));
        assertEquals("Username should not be empty", exception.getMessage());
    }

    @Test
    public void settingPasswordEmptyShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> loginDTO.setPassword(" "));
        assertEquals("Password should not be empty", exception.getMessage());
    }

}