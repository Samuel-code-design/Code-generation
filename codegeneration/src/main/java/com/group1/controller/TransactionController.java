package com.group1.controller;

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

@RestController
@RequestMapping("Account/Transaction")
public class TransactionController {

    private TransactionService transService;
    private AccountService accountService;

    public TransactionController(TransactionService transService, AccountService accountService) {
        this.transService = transService;
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity makeTransaction(@RequestBody Transaction trans){
        transService.createTransaction(trans);
        accountService.updateBalance(-trans.getAmount(), trans.getAccountFrom());
        accountService.updateBalance(+trans.getAmount(), trans.getAccountTo());
        return ResponseEntity.status(HttpStatus.CREATED).body(trans.getId());
    }

}
