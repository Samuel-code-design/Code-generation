package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.Role;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.TransactionRepository;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    public void addTransaction (Transaction transaction) throws Exception {
        if(transaction.getAmount() < 0.01)
            throw new Exception(); //transaction amount is to low

        User perfUser;
        //bestaat de performing user
        if(!userRepository.existsById(transaction.getPerformingUser()))
            throw new Exception(); //user bestaat niet
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
                throw new Exception(); //accountFrom is niet van de user
        }

        Account accountFrom = accountRepository.findOneByIban(transaction.getAccountFrom());
        //check als accountFrom niet leeg is
        Account accountTo = accountRepository.findOneByIban(transaction.getAccountTo());
        //check als accountTo niet leeg is

        //check als accountFrom van type savings is en als accountTo ook van gebruiker is
        if(accountFrom.getType() == Account.TypeEnum.SAVING && !userAccounts.contains(accountTo))
            throw new Exception(); //accountTo is niet van user

        //check als accountTo van type savings is en als accountFrom ook van gebruiker is
        if(accountTo.getType() == Account.TypeEnum.SAVING && !userAccounts.contains(accountFrom))
            throw new Exception(); //accountFrom is niet van user


        if(perfUser.getTransactionLimit() < transaction.getAmount())
            throw new Exception(); //Transaction amount exceeds transaction limit

        if(accountFrom.getAbsoluteLimit() > (accountFrom.getBalance() - transaction.getAmount()))
            throw new Exception(); //Transaction will make the balance of accountFrom under the absolute limit

        //CHECK ALS ALLE TRANSACTIES AMOUNTS BIJ ELKAAR VAN VANDAAG VAN DE PERFORMING USER NIET HOGER IS DAN perfUser.getDayLimit()

        transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }
}
