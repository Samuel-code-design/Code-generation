/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.InlineResponse400;
import io.swagger.model.dto.CreateUserDTO;
import io.swagger.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-17T12:11:50.256Z[GMT]")
@Validated
public interface UsersApi {

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
    ResponseEntity<User> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "withdraw", required=true, schema=@Schema()) @Valid @RequestBody CreateUserDTO body);

//    @Operation(summary = "lock user by email", description = "Lets an employee lock a user by an email", security = {
//        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Employee" })
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "Successfully updated user information"),
//
//        @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),
//
//        @ApiResponse(responseCode = "401", description = "Authorization failed"),
//
//        @ApiResponse(responseCode = "404", description = "User not found") })
//    @RequestMapping(value = "/users/{email}",
//        produces = { "application/json" },
//        method = RequestMethod.PUT)
//    ResponseEntity<Void> lockUserByEmail(@Parameter(in = ParameterIn.PATH, description = "the email", required=true, schema=@Schema()) @PathVariable("email") String email);

//    @Operation(summary = "lock user by email", description = "Lets an employee lock a user by an email", security = {
//            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Employee" })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully updated user information"),
//
//            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),
//
//            @ApiResponse(responseCode = "401", description = "Authorization failed"),
//
//            @ApiResponse(responseCode = "404", description = "User not found") })
//    @RequestMapping(value = "/user",
//            produces = { "application/json" },
//            method = RequestMethod.PUT)
//    ResponseEntity<Void> lockUserByEmail(@Parameter(in = ParameterIn.QUERY, description = "the email", required=true, schema=@Schema()) @Valid @RequestParam(value = "email", required = true) String email);


    @Operation(summary = "lock user by id", description = "Lets an employee lock a user by an id", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Employee" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated user information"),

            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),

            @ApiResponse(responseCode = "401", description = "Authorization failed"),

            @ApiResponse(responseCode = "404", description = "User not found") })
    @RequestMapping(value = "/users/{id}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<User> lockUserById(@Parameter(in = ParameterIn.PATH, description = "the userID", required=true, schema=@Schema()) @PathVariable("id") Long id);


    @Operation(summary = "update information of a user", description = "Lets an employee update user information", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Employee" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated user information"),

            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),

            @ApiResponse(responseCode = "401", description = "Authorization failed") })
    @RequestMapping(value = "/users",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<User> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody User body);


    @Operation(summary = "get users", description = "Returns a list of Users.", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Employee" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the Users", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),

            @ApiResponse(responseCode = "400", description = "bad input parameter"),

            @ApiResponse(responseCode = "401", description = "Authorization failed") })
    @RequestMapping(value = "/users",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<User>> getUsers(@Parameter(in = ParameterIn.QUERY, description = "Search a user by a string maching a username, firstname, lastname or email" ,schema=@Schema()) @Valid @RequestParam(value = "searchstring", required = false) String searchstring) throws IOException;

//    @Operation(summary = "get a user by it's email", description = "Returns a user with specified email", security = {
//        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Employee" })
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "the User", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
//
//        @ApiResponse(responseCode = "400", description = "bad input parameter"),
//
//        @ApiResponse(responseCode = "401", description = "Authorization failed"),
//
//        @ApiResponse(responseCode = "404", description = "User not found") })
//    @RequestMapping(value = "/users/{email}",
//        produces = { "application/json" },
//        method = RequestMethod.GET)
//    ResponseEntity<User> userByEmail(@Parameter(in = ParameterIn.PATH, description = "the userEmail", required=true, schema=@Schema()) @PathVariable("email") String email) throws IOException;

//    @Operation(summary = "get a user by it's email", description = "Returns a user with specified email", security = {
//            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Employee" })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "the User", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
//
//            @ApiResponse(responseCode = "400", description = "bad input parameter"),
//
//            @ApiResponse(responseCode = "401", description = "Authorization failed"),
//
//            @ApiResponse(responseCode = "404", description = "User not found") })
//    @RequestMapping(value = "/user",
//            produces = { "application/json" },
//            method = RequestMethod.GET) //path veranderd omdat hij anders te veel lijkt op: GetById of GetUsers
//    ResponseEntity<User> userByEmail(@Parameter(in = ParameterIn.QUERY, description = "the userEmail", schema=@Schema()) @Valid @RequestParam(value = "email", required = true) String email) throws IOException;


    @Operation(summary = "get a user by id", description = "Returns a user with specified id", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Employee" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the User", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),

            @ApiResponse(responseCode = "400", description = "bad input parameter"),

            @ApiResponse(responseCode = "401", description = "Authorization failed"),

//            @ApiResponse(responseCode = "204", description = "User not found"),

            @ApiResponse(responseCode = "404", description = "User not found") })

    @RequestMapping(value = "/users/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<User> userById(@Parameter(in = ParameterIn.PATH, description = "the userID", required=true, schema=@Schema()) @PathVariable("id") Long id) throws IOException;

}
