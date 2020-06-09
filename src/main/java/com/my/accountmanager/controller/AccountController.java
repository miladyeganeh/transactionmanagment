package com.my.accountmanager.controller;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.model.dto.AccountDTO;
import com.my.accountmanager.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author M.Yeganeh on 01/06/2020.
 */
@RestController
@RequestMapping("api/v1/accounts") //http.localhost:8080/api/v1/account
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ApiOperation(value = "View a list of available languages", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id) {
        Optional<AccountEntity> accountEntity = accountService.getById(id);
        if (accountEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AccountDTO.to(accountEntity.get()));
    }

    @ApiOperation(value = "View a list of available languages", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@ModelAttribute AccountDTO accountDTO) {
        AccountDTO savedAccount = accountService.persist(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    @ApiOperation(value = "View a list of available languages", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PutMapping
    public ResponseEntity<AccountDTO> updateAccount(@ModelAttribute AccountDTO accountDTO) {
        AccountDTO savedAccount = accountService.persist(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    @ApiOperation(value = "View a list of available languages", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        Optional<AccountEntity> detachedAccount = accountService.getById(id);
        //TODO handle Not found
        detachedAccount.ifPresent(accountService::evict);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
