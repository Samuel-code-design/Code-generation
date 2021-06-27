package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    private Transaction transaction;
    @BeforeEach
    public void Setup() {
        transaction = new Transaction(new NewTransaction(10.10, LocalDateTime.now(), "1", "2", 1L));
    }

    @Test
    public void createTransactionNotNull(){
        Transaction transaction2 = new Transaction();
        assertNotNull(transaction2);
    }

    @Test
    public void setGetTransactionId(){
        int id = 1;
        assertEquals(id, transaction.id(1).getId());
        assertNotNull(transaction.id(1));
    }

    @Test
    public void setAndGetTransactionId(){
        int id = 1;
        transaction.setId(id);
        assertEquals(id, transaction.getId());
    }

    @Test
    public void transactionEqualsTransactionTrue(){
        assertTrue(transaction.equals(transaction));
    }

    @Test
    public void transactionEqualsTransactionFalse(){
        Transaction transaction1 = new Transaction();
        assertFalse(transaction.equals(transaction1));
    }

    @Test
    public void nullEqualsTransactionFalse(){
        assertFalse(transaction.equals(null));
    }

    @Test
    public void hashCodeNotNull(){
        assertNotNull(transaction.hashCode());
    }

    @Test
    public void toStringNotNull(){
        assertNotNull(transaction.toString());
    }
}
