package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.AccountType;
import io.swagger.model.User;
import io.swagger.model.dto.AccountCreateDTO;
import io.swagger.model.dto.AccountResponseDTO;
import io.swagger.model.dto.AccountUpdateDTO;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeRepository userRepository;

    public AccountService() {
    }

    public void updateBalance(double amount, String iban){
        if(accountRepository.existsByiban(iban)){
            Account account = accountRepository.findOneByIban(iban);

            double balance = account.getBalance();
            double newBalance = balance + amount;
            account.setBalance(newBalance);

           accountRepository.save(account);
        }else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Account found for this iban");
        }
   }

    public String generateIban(){
        boolean unique = false;
        String newIban = "";
        while (!unique) {
            //NLxxINHO0xxxxxxxxx
            Random rand = new Random();
            int max = 9;
            StringBuilder ibanStringbuilder = new StringBuilder("NL");
            for (int i = 0; i < 11; i++) {
                int randomNumber = rand.nextInt(max);
                ibanStringbuilder.append(randomNumber);
                if (i == 1) {
                    ibanStringbuilder.append("INHO0");
                }
            }

            newIban = ibanStringbuilder.toString();

            unique = !accountRepository.existsByiban(newIban);
        }

        return newIban;
    }

    public List<AccountResponseDTO> accountToResponseDTOList(List<Account> accounts){
        List<AccountResponseDTO> accountList = new ArrayList<>();
        for(Account acc : accounts){
            AccountResponseDTO accResponse = accountToResponseDTO(acc);
            accountList.add(accResponse);
        }
        return accountList;
    }

    public AccountResponseDTO accountToResponseDTO(Account acc){
        return new AccountResponseDTO(acc.getIban(), acc.getType(), acc.getBalance(),
                acc.getAbsoluteLimit(), acc.getLocked(), acc.getUser().getId(),
                acc.getUser().getFirstName() + " " + acc.getUser().getLastName());
    }


    public AccountResponseDTO getAccountByIban(String iban){
        if(accountRepository.existsByiban(iban)){
            Account acc =  accountRepository.findOneByIban(iban);
            return accountToResponseDTO(acc);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No account found for this iban");
        }
    }

    public List<AccountResponseDTO> getAccountsByUserId(Long userId){
        if(!userRepository.existsByid(userId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with this id");
        }else if(!accountRepository.existsByuserId(userId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No accounts found for this user");
        }else{
            List<Account> accounts = accountRepository.findByUserId(userId);
            return accountToResponseDTOList(accounts);
        }
    }

    public List<AccountResponseDTO> getAllAccounts(){
        List<Account> accounts = accountRepository.findAll();
        return accountToResponseDTOList(accounts);
    }

    public AccountResponseDTO updateAccount(AccountUpdateDTO account){
        Account acc = accountRepository.findOneByIban(account.getIban());
        if(userRepository.existsByid(account.getUserId())){
            acc.setAbsoluteLimit(account.getAbsoluteLimit());
            acc.setType(account.getType());
            acc.setLocked(account.getLocked());
            User u = userRepository.findByIdEquals(account.getUserId());
            acc.setUser(u);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no user for this userId");
        }

        accountRepository.save(acc);
        return accountToResponseDTO(acc);
    }

    public AccountResponseDTO addAccount(AccountCreateDTO acc){
        if(userRepository.existsByid(acc.getUserId())){
            String iban = generateIban();
            Account account = new Account();
            account.setIban(iban);
            account.setAbsoluteLimit(acc.getAbsoluteLimit());
            account.setType(acc.getType());
            account.setBalance(0.00);
            account.setLocked(acc.getLocked());
            //set user
            User u = userRepository.findByIdEquals(acc.getUserId());
            account.setUser(u);
            accountRepository.save(account);
            return accountToResponseDTO(account);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no user for this UserId");
        }
    }

    public AccountResponseDTO lockAccountByIban(String iban){
        if(accountRepository.existsByiban(iban)){
            Account acc = accountRepository.findOneByIban(iban);
            acc.setLocked(true);
            accountRepository.save(acc);
            return accountToResponseDTO(acc);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No account found for this Iban");
        }
    }

        public void generateCurrentAccountForUser(User u){
        Double ABSOLUTE_LIMIT = 0.0;
        Account acc = new Account();

        acc.setUser(u);
        acc.setBalance(0.0);
        acc.setAbsoluteLimit(ABSOLUTE_LIMIT);
        acc.setIban(generateIban());
        acc.locked(false);
        acc.type(AccountType.CURRENT);
        accountRepository.save(acc);
    }
}
