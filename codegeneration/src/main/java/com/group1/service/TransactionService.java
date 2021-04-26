package com.group1.service;

import com.group1.model.Transaction;
import com.group1.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionService() {
    }

    public List<Transaction> getAllTransactions() {return (List<Transaction>) transactionRepository.findAll();}

    public void createTransaction(Transaction trans){
        transactionRepository.save(trans);
    }
}
