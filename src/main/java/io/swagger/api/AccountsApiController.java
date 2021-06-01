package io.swagger.api;

import io.swagger.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.dto.AccountDTO;
import io.swagger.models.Response;
import io.swagger.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-17T12:11:50.256Z[GMT]")
@RestController
@RequestMapping("Accounts")
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

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addAccount(@Parameter(in = ParameterIn.DEFAULT, description = "account", required=true, schema=@Schema()) @Valid @RequestBody AccountDTO acc) {

        accountService.addAccount(acc);
        return ResponseEntity.status(HttpStatus.CREATED).body(acc);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> allAccounts(@Min(0)@Parameter(in = ParameterIn.QUERY, description = "number of accounts to skip for pagination" ,schema=@Schema(allowableValues={  }
)) @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(0) @Max(100) @Parameter(in = ParameterIn.QUERY, description = "maximum number of accounts to return" ,schema=@Schema(allowableValues={  }, maximum="100"
)) @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        //TO DO: Skip and Limit
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.status(200).body(accounts);
    }

    @RequestMapping(value = "/iban/{iban}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> findAccountsByIban(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("iban") String iban) {
        List<Account> accounts = accountService.getAllAccounts();

        List<Account> foundAccs = new ArrayList<Account>();
        for(Account a : accounts){
            if(a.getIban().contains(iban)){
                foundAccs.add(a);
            }
        }

        if(foundAccs == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(foundAccs);
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(foundAccs);
        }

    }

    @RequestMapping(value = "/userId/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> findAccountsByUserId(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Long userId) {
        List<Account> foundAccs = accountService.getAccountsByUserId(userId);

        if(foundAccs == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(foundAccs);
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(foundAccs);
        }
    }

    @RequestMapping(value = "/lock/{iban}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity lockAccount(@Parameter(in = ParameterIn.PATH, description = "iban to lock", required=true, schema=@Schema()) @PathVariable("iban") String iban, @Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema()) @RequestHeader(value="api_key", required=false) String apiKey) {
        Account acc = accountService.getAccountByIban(iban);
        if (acc != null){
            acc.setLocked(true);
            return ResponseEntity.status(HttpStatus.OK).body(acc);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(acc);
        }

    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAccount(@Parameter(in = ParameterIn.DEFAULT, description = "account", required=true, schema=@Schema()) @Valid @RequestBody Account body) {
        accountService.updateAccount(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(body.getIban());
    }

}
