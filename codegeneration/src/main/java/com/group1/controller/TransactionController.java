package com.group1.controller;

import com.group1.model.AccountType;
import com.group1.model.Transaction;
import com.group1.service.AccountService;
import com.group1.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("Transaction")
public class TransactionController {

    private TransactionService transService;
    private AccountService accountService;

    public TransactionController(TransactionService transService, AccountService accountService) {
        this.transService = transService;
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity showAllTransactions(){
        List<Transaction> trans = transService.getAllTransactions();
        return ResponseEntity.status(200).body(trans);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity makeTransaction(@RequestBody Transaction trans){
        if(trans.getPerformingUser().getTransactionLimit() >= trans.getAmount()) {
            double amount = trans.getAmount();
            double posAmount = Math.abs(amount);
            trans.setAmount(posAmount);
            trans.setTimestamp(LocalDateTime.now());

            transService.createTransaction(trans);

            if(trans.getAccountFrom().getType() == AccountType.SAVING || trans.getAccountTo().getType() == AccountType.SAVING){
                //if(user that is logged in owns the account from & account to) {       //ik weet nog niet hoe de login werkt
                accountService.updateBalance(-trans.getAmount(), trans.getAccountFrom());
                accountService.updateBalance(+trans.getAmount(), trans.getAccountTo());
            }else
            {
                //if(user that is logged in only owns account from)
                accountService.updateBalance(-trans.getAmount(), trans.getAccountFrom());
                accountService.updateBalance(+trans.getAmount(), trans.getAccountTo());
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(trans.getId());
       }else{
            return ResponseEntity.ok("The amount goes over your transaction limit");
       }

    }

}
