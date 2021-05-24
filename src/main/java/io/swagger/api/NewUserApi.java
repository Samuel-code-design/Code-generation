package io.swagger.api;

import io.swagger.model.InlineResponse400;
import io.swagger.model.NewUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-17T12:11:50.256Z[GMT]")
@Validated
@Repository
public interface NewUserApi extends JpaRepository<NewUser, Long> {

    @Operation(summary = "create a new user", description = "Lets an employee register a new user", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Employee" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the User"),

            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),

            @ApiResponse(responseCode = "401", description = "Authorization failed") })
    @RequestMapping(value = "/users",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Void> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "withdraw", required=true, schema=@Schema()) @Valid @RequestBody NewUser body);

}
