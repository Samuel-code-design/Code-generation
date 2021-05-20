package io.swagger.api;

import io.swagger.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-17T12:11:50.256Z[GMT]")
@RestController
public class AccountsApiController implements AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addAccount(@Parameter(in = ParameterIn.DEFAULT, description = "account", required=true, schema=@Schema()) @Valid @RequestBody Account body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Account>> allAccounts(@Min(0)@Parameter(in = ParameterIn.QUERY, description = "number of accounts to skip for pagination" ,schema=@Schema(allowableValues={  }
)) @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(0) @Max(100) @Parameter(in = ParameterIn.QUERY, description = "maximum number of accounts to return" ,schema=@Schema(allowableValues={  }, maximum="100"
)) @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Account>>(objectMapper.readValue("[ {\n  \"balance\" : 0.8008281904610115,\n  \"absoluteLimit\" : 6.027456183070403,\n  \"iban\" : \"NL01INHO0123456789\",\n  \"type\" : \"SAVING\",\n  \"userId\" : 1\n}, {\n  \"balance\" : 0.8008281904610115,\n  \"absoluteLimit\" : 6.027456183070403,\n  \"iban\" : \"NL01INHO0123456789\",\n  \"type\" : \"SAVING\",\n  \"userId\" : 1\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Account>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Account>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Account>> findAccountsByIban(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("iban") Integer iban) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Account>>(objectMapper.readValue("[ {\n  \"balance\" : 0.8008281904610115,\n  \"absoluteLimit\" : 6.027456183070403,\n  \"iban\" : \"NL01INHO0123456789\",\n  \"type\" : \"SAVING\",\n  \"userId\" : 1\n}, {\n  \"balance\" : 0.8008281904610115,\n  \"absoluteLimit\" : 6.027456183070403,\n  \"iban\" : \"NL01INHO0123456789\",\n  \"type\" : \"SAVING\",\n  \"userId\" : 1\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Account>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Account>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Account>> findAccountsByUserId(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Account>>(objectMapper.readValue("[ {\n  \"balance\" : 0.8008281904610115,\n  \"absoluteLimit\" : 6.027456183070403,\n  \"iban\" : \"NL01INHO0123456789\",\n  \"type\" : \"SAVING\",\n  \"userId\" : 1\n}, {\n  \"balance\" : 0.8008281904610115,\n  \"absoluteLimit\" : 6.027456183070403,\n  \"iban\" : \"NL01INHO0123456789\",\n  \"type\" : \"SAVING\",\n  \"userId\" : 1\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Account>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Account>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> lockAccount(@Parameter(in = ParameterIn.PATH, description = "iban to lock", required=true, schema=@Schema()) @PathVariable("iban") String iban,@Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema()) @RequestHeader(value="api_key", required=false) String apiKey) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateAccount(@Parameter(in = ParameterIn.DEFAULT, description = "account", required=true, schema=@Schema()) @Valid @RequestBody Account body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
