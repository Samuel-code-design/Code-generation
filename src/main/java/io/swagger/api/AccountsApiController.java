package io.swagger.api;

import io.swagger.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.dto.AccountDTO;
import io.swagger.service.AccountService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-17T12:11:50.256Z[GMT]")
@RestController
public class AccountsApiController implements AccountsApi {
    //TO DO search by username
    @Autowired
    private AccountService accountService;

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity addAccount(@Parameter(in = ParameterIn.DEFAULT, description = "account", required=true, schema=@Schema()) @Valid @RequestBody AccountDTO acc) {
        Account account = accountService.addAccount(acc);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    public ResponseEntity<List<Account>> allAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.status(200).body(accounts);
    }

    public ResponseEntity<Account> findAccountByIban(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("iban") String iban) {
        Account acc = accountService.getAccountByIban(iban);
        return ResponseEntity.status(HttpStatus.OK).body(acc);
    }

    public ResponseEntity<List<Account>> findAccountsByUserId(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Long userId) {
        List<Account> foundAccs = accountService.getAccountsByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(foundAccs);
    }

    public ResponseEntity lockAccount(@Parameter(in = ParameterIn.PATH, description = "iban to lock", required=true, schema=@Schema()) @PathVariable("iban") String iban) {
        Account acc = accountService.lockAccountByIban(iban);
        return ResponseEntity.status(HttpStatus.OK).body(acc);

    }

    public ResponseEntity updateAccount(@Parameter(in = ParameterIn.DEFAULT, description = "account", required=true, schema=@Schema()) @Valid @RequestBody Account body) {
        Account acc = accountService.updateAccount(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(acc);
    }

}
