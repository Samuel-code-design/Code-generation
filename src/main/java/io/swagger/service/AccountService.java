package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountService() {
    }
    public String generateIban(){
        return "NL01INHO0000000001";
    }

    public void updateBalance(double amount, Account account){
        double balance = account.getBalance();
        double newbalance = balance + amount;
        account.setBalance(newbalance);

        accountRepository.save(account);
    }

    public void updateAccount(Account account){
        accountRepository.save(account);
    }

    public List<Account> getAccountsByIban(String iban){return accountRepository.findByIban(iban);}

    public Account getAccountByIban(String iban){return accountRepository.findOneByIban(iban);}

    public List<Account> getAccountsByUserId(Long userId){
        return accountRepository.findByUserId(userId);
    }

    public List<Account> getAllAccounts(){
        return (List<Account>) accountRepository.findAll();
    }

    public void addAccount(Account acc){
        accountRepository.save(acc);
    }



}
