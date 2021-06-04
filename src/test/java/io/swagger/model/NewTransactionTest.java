package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewTransactionTest {

    private NewTransaction newTransaction;
    @BeforeEach
    public void Setup() {
        newTransaction = new NewTransaction();
    }

    @Test
    void settingNegativeAmountShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> newTransaction.setAmount(-10.0));
        assertEquals("Amount cannot be negative.", exception.getMessage());
    }

    @Test void settingSameAccountToAndAccountFromShouldThrowException(){
        newTransaction.setAccountTo("NL01INHO0123456789");
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> newTransaction.setAccountFrom("NL01INHO0123456789"));
        assertEquals("Transactions cannot go to the same account.", exception.getMessage());
    }
}