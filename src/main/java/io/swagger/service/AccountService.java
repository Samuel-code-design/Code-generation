package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.dto.AccountDTO;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public AccountService() {
    }

//    public void updateBalance(double amount, Account account){
//        double balance = account.getBalance();
//        double newbalance = balance + amount;
//        account.setBalance(newbalance);
//
//        accountRepository.save(account);
//    }

    public String generateIban(){
        boolean unique = false;
        String newIban = "";
        while(!unique){
            //NLxxINHO0xxxxxxxxx
            Random rand = new Random();
            int max = 9;
            StringBuilder ibanStringbuilder = new StringBuilder("NL");
            for(int i = 0; i < 11; i++)
            {
                int randomNumber = rand.nextInt(max);
                ibanStringbuilder.append(randomNumber);
                if(i == 1){
                    ibanStringbuilder.append("INHO0");
                }
            }

            newIban = ibanStringbuilder.toString();

            unique = !accountRepository.existsByiban(newIban);
        }

        return newIban;
    }

    public List<Account> getAccountsByIban(String iban){return accountRepository.findByIban(iban);}

    public Account getAccountByIban(String iban){return accountRepository.findOneByIban(iban);}

    public List<Account> getAccountsByUserId(Long userId){
        return accountRepository.findByUserId(userId);
    }

    public List<Account> getAllAccounts(){
        return (List<Account>) accountRepository.findAll();
    }

    public void updateAccount(Account account){
        Account acc = getAccountByIban(account.getIban());

        acc.setAbsoluteLimit(account.getAbsoluteLimit());
        acc.setType(account.getType());
        acc.setBalance(account.getBalance());
        acc.setLocked(account.getLocked());
        accountRepository.save(account);
    }

    public void addAccount(AccountDTO acc){
        if(userRepository.existsByid(acc.getUserId())){
            String iban = generateIban();
            Account account = new Account();
            account.setIban(iban);
            account.setAbsoluteLimit(acc.getAbsoluteLimit());
            account.setType(acc.getType());
            account.setBalance(acc.getBalance());
            account.setLocked(acc.getLocked()); account.setUserId(acc.getUserId());
            accountRepository.save(account);
        }else{
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User Id doesn't exist");
        }

    }



}
