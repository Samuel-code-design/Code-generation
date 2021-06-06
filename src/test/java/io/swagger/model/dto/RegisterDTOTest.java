package io.swagger.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterDTOTest {

    private RegisterDTO registerDTO;

    @BeforeEach
    public void Setup() {
        registerDTO = new RegisterDTO("JD0001", "Wachtwoord1#", "John", "Doe",
                "JohnDoe@gmail.com", "06 12345678");
    }

    @Test
    public void registerUserShouldNotBeNull() {
        assertNotNull(registerDTO);
    }

    @Test
    public void settingUserNameEmptyShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> registerDTO.setUsername(" "));
        assertEquals("Username should not be empty", exception.getMessage());
    }
    @Test
    public void settingFirstNameEmptyShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> registerDTO.setFirstName(" "));
        assertEquals("Firstname should not be empty", exception.getMessage());
    }
    @Test
    public void settingLastNameEmptyShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> registerDTO.setLastName(" "));
        assertEquals("Lastname should not be empty", exception.getMessage());
    }
    @Test
    public void settingPasswordEmptyShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> registerDTO.setPassword(" "));
        assertEquals("Password should not be empty", exception.getMessage());
    }
    @Test
    public void settingEmailEmptyShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> registerDTO.setEmail(" "));
        assertEquals("Email should not be empty", exception.getMessage());
    }
    @Test
    public void settingPhoneEmptyShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> registerDTO.setPhone(" "));
        assertEquals("Phone should not be empty", exception.getMessage());
    }
}