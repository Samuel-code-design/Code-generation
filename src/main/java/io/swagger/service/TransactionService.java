package io.swagger.service;

import io.swagger.model.*;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.TransactionRepository;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    public void addTransaction (Transaction transaction) {
        if(transaction.getAccountTo().equals("NL01INHO0000000001")
                || transaction.getAccountFrom().equals("NL01INHO0000000001"))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Transactions to or from the bank account are prohibited.");

        if(transaction.getAmount() < 0.01)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Transaction amount in below the minimum.");

        User perfUser;
        //bestaat de performing user
        if(!userRepository.existsById(transaction.getPerformingUser()))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "User does not exists.");
        //haal de performing user op
        perfUser = userRepository.getOne(transaction.getPerformingUser());

        //is de user een customer
        List<Account> userAccounts = accountRepository.findByUserId(transaction.getPerformingUser());
        if(perfUser.getRoles().contains(Role.ROLE_CUSTOMER)){
            //is accountFrom wel van de customer
            Boolean accountIsFromCustomer = false;
            for (Account acc : userAccounts) {
                if(acc.getUser().getId() == transaction.getPerformingUser())
                    accountIsFromCustomer = true;
            }
            if(!accountIsFromCustomer)
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                        "The account which is sending the money is not from you.");
        }

        Account accountFrom = accountRepository.findOneByIban(transaction.getAccountFrom());
        if(accountFrom == null)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "The account which is sending the money does not exists.");
        Account accountTo = accountRepository.findOneByIban(transaction.getAccountTo());
        if(accountTo == null)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "The account which is receiving the money does not exists.");

        //check als accountFrom van type savings is en als accountTo ook van gebruiker is
        if(accountFrom.getType() == AccountType.SAVING && !userAccounts.contains(accountTo))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Saving account can only send to accounts that are from you.");

        //check als accountTo van type savings is en als accountFrom ook van gebruiker is
        if(accountTo.getType() == AccountType.SAVING && !userAccounts.contains(accountFrom))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "You can only send to your own saving accounts.");


        if(perfUser.getTransactionLimit() < transaction.getAmount())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Transaction amount exceeds transaction limit.");

        if(accountFrom.getAbsoluteLimit() > (accountFrom.getBalance() - transaction.getAmount()))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Transaction will make the balance of accountFrom under the absolute limit.");

        //check als de daily limit niet overschreven wordt
        List<Transaction> transactionsToday = transactionRepository.findAllByTimestampAfter(
                LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT));
        double spendBalanceToday = 0;
        if(!transactionsToday.isEmpty()){
            for (Transaction trans : transactionsToday) {
                spendBalanceToday =  spendBalanceToday + trans.getAmount();
            }
        }
        spendBalanceToday = spendBalanceToday + transaction.getAmount();
        if(spendBalanceToday > accountFrom.getUser().getDayLimit())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Transaction will go over the daily transaction limit.");

        accountService.updateBalance(transaction.getAmount(), accountTo.getIban());
        accountService.updateBalance((-1 * transaction.getAmount()), accountFrom.getIban());
        transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAllTransactionsByDate(String date) {
        try{
            LocalDateTime start = LocalDateTime.of(LocalDate.parse(date), LocalTime.MIDNIGHT);
            return transactionRepository.findAllByTimestampBetween(start, start.plusDays(1));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Invalid date input.");
        }
    }
}
