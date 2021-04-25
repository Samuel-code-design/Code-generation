package com.group1.controller;

import com.group1.model.Account;

import com.group1.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllAccounts(){
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.status(200).body(accounts);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = {"username"})
    public ResponseEntity getAccountsByUser(@RequestParam("username") String username){
        try{
            List<Account> accounts = accountService.getAccountsByUserName(username);
            return ResponseEntity.status(HttpStatus.OK).body(accounts);
        } catch (IllegalArgumentException iae){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAccountById(@PathVariable long id){
        try{
            Account acc = accountService.getAccountById(id);
            return ResponseEntity.status(HttpStatus.OK).body(acc);
        }catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAccount(@RequestBody Account account){
        accountService.addAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(account.getId());
    }
}
