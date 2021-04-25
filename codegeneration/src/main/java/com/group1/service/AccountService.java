package com.group1.service;

import com.group1.model.Account;
import com.group1.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountService() {
    }

    public void updateBalance(double amount, Account account){
        double balance = account.getBalance();
        double newbalance = balance + amount;
        account.setBalance(newbalance);

        accountRepository.save(account);
    }

    public List<Account> getAccountsByUserName(String uname){
       return accountRepository.findByUserUsername(uname);
    }

    public List<Account> getAllAccounts(){
        return (List<Account>) accountRepository.findAll();
    }

    public void addAccount(Account acc){
        accountRepository.save(acc);
    }

    public Account getAccountById(long id){
        return accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }


}
