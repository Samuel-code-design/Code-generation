package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NewTransactionTest {

    private NewTransaction newTransaction;
    @BeforeEach
    public void Setup() {
        newTransaction = new NewTransaction(10.10, LocalDateTime.now(), "1", "2", 1L);
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

    @Test
    void settingAccountFromAsBankAccountShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> newTransaction.setAccountFrom("NL01INHO0000000001"));
        assertEquals("Transactions to or from are prohibited.", exception.getMessage());
    }

    @Test
    void settingAccountToAsBankAccountShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> newTransaction.setAccountTo("NL01INHO0000000001"));
        assertEquals("Transactions to or from are prohibited.", exception.getMessage());
    }

    @Test
    public void createNewTransactionNotNull(){
        NewTransaction transaction = new NewTransaction();
        assertNotNull(transaction);
    }

    @Test
    public void setGetNewTransactionAmount(){
        double amount = 10.20;
        assertEquals(amount, newTransaction.amount(10.20).getAmount());
    }

    @Test
    public void setAndGetNewTransactionAmount(){
        double amount = 10.20;
        newTransaction.setAmount(amount);
        assertEquals(amount, newTransaction.getAmount());
    }

    @Test
    public void setGetNewTransactionTimeStamp(){
        LocalDateTime dateTime = LocalDateTime.now();
        assertEquals(dateTime, newTransaction.timestamp(dateTime).getTimestamp());
    }

    @Test
    public void setAndGetNewTransactionTimeStamp(){
        LocalDateTime dateTime = LocalDateTime.now();
        newTransaction.setTimestamp(dateTime);
        assertEquals(dateTime, newTransaction.getTimestamp());
    }

    @Test
    public void setGetNewTransactionAccountFrom(){
        String accountFrom = "3";
        assertEquals(accountFrom, newTransaction.accountFrom(accountFrom).getAccountFrom());
    }

    @Test
    public void setAndGetNewTransactionAccountFrom(){
        String accountFrom = "3";
        newTransaction.setAccountFrom(accountFrom);
        assertEquals(accountFrom, newTransaction.getAccountFrom());
    }

    @Test
    public void setGetNewTransactionAccountTo(){
        String accountTo = "4";
        assertEquals(accountTo, newTransaction.accountTo(accountTo).getAccountTo());
    }

    @Test
    public void setAndGetNewTransactionAccountTo(){
        String accountTo = "4";
        newTransaction.setAccountTo(accountTo);
        assertEquals(accountTo, newTransaction.getAccountTo());
    }

    @Test
    public void setGetNewTransactionPerformingUser(){
        long performingUser = 2l;
        assertEquals(performingUser, newTransaction.performingUser(performingUser).getPerformingUser());
    }

    @Test
    public void setAndGetNewTransactionPerformingUser(){
        long performingUser = 2l;
        newTransaction.setPerformingUser(performingUser);
        assertEquals(performingUser, newTransaction.getPerformingUser());
    }

    @Test
    public void newTransactionEqualsNewTransactionTrue(){
        assertTrue(newTransaction.equals(newTransaction));
    }

    @Test
    public void newTransactionEqualsNewTransactionFalse(){
        NewTransaction transaction1 = new NewTransaction();
        assertFalse(newTransaction.equals(transaction1));
    }

    @Test
    public void nullEqualsNewTransactionFalse(){
        assertFalse(newTransaction.equals(null));
    }

    @Test
    public void hashCodeNotNull(){
        assertNotNull(newTransaction.hashCode());
    }

    @Test
    public void toStringNotNull(){
        assertNotNull(newTransaction.toString());
    }
}