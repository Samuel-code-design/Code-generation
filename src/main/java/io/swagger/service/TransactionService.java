package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.Role;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.TransactionRepository;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if(transaction.getAmount() < 0.01)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Transaction amount in below the minimum.");

        User perfUser;
        //bestaat de performing user
        if(!userRepository.existsById(transaction.getPerformingUser()))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User does not exists.");
        //haal de performing user op
        perfUser = userRepository.getOne(transaction.getPerformingUser());

        //is de user een customer
        List<Account> userAccounts = accountRepository.findByUserId(transaction.getPerformingUser());
        if(perfUser.getRoles().contains(Role.ROLE_CUSTOMER)){
            //is accountFrom wel van de customer
            Boolean accountIsFromCustomer = false;
            for (Account acc : userAccounts) {
                if(acc.getUserId() == transaction.getPerformingUser())
                    accountIsFromCustomer = true;
            }
            if(!accountIsFromCustomer)
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The account which is sending the money is not from you.");
        }

        Account accountFrom = accountRepository.findOneByIban(transaction.getAccountFrom());
        //check als accountFrom niet leeg is
        Account accountTo = accountRepository.findOneByIban(transaction.getAccountTo());
        //check als accountTo niet leeg is

        //check als accountFrom van type savings is en als accountTo ook van gebruiker is
        if(accountFrom.getType() == Account.TypeEnum.SAVING && !userAccounts.contains(accountTo))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Saving account can only send to accounts that are from you.");

        //check als accountTo van type savings is en als accountFrom ook van gebruiker is
        if(accountTo.getType() == Account.TypeEnum.SAVING && !userAccounts.contains(accountFrom))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "You can only send to your own saving accounts.");


        if(perfUser.getTransactionLimit() < transaction.getAmount())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Transaction amount exceeds transaction limit.");

        if(accountFrom.getAbsoluteLimit() > (accountFrom.getBalance() - transaction.getAmount()))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Transaction will make the balance of accountFrom under the absolute limit.");

        //CHECK ALS ALLE TRANSACTIES AMOUNTS BIJ ELKAAR VAN VANDAAG VAN DE PERFORMING USER NIET HOGER IS DAN perfUser.getDayLimit()



        transactionRepository.save(transaction);
        accountService.updateBalance(transaction.getAmount(), accountTo.getIban());
        accountService.updateBalance((-1 * transaction.getAmount()), accountFrom.getIban());
    }

    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }
}
