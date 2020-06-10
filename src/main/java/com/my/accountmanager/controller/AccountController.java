package com.my.accountmanager.controller;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.model.dto.AccountDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.enums.ResponseCode;
import com.my.accountmanager.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

/**
 * @author M.Yeganeh on 01/06/2020.
 */
@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ApiOperation(value = "View a specific Account Details", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved account details"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO<AccountDTO>> getAccount(@PathVariable Long id) {
        ResponseDTO<AccountDTO> response = new ResponseDTO<>();
        Optional<AccountEntity> accountEntity = accountService.getById(id);
        if (accountEntity.isEmpty()) {
            response.setCode(ResponseCode.NOT_FOUND_CONTENT);
            response.setMessage("Account was not fount.");
            response.setDate(new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setDate(new Date());
        response.setCode(ResponseCode.FOUND_CONTENT);
        response.setData(AccountDTO.to(accountEntity.get()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Create Account", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The Account successfully is created"),
            @ApiResponse(code = 500, message = "The Account could not be created")
    })
    @PostMapping
    public ResponseEntity<ResponseDTO<AccountDTO>> createAccount(@ModelAttribute AccountDTO accountDTO) {
        ResponseDTO<AccountDTO> response = new ResponseDTO<>();
        AccountEntity savedAccount = new AccountEntity();
        try {
            savedAccount = accountService.save(AccountDTO.from(accountDTO));
        } catch (IllegalArgumentException ex) {
            response.setDate(new Date());
            response.setCode(ResponseCode.INTERNAL_ERROR);
            response.setMessage("Account can not be created;");
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        response.setDate(new Date());
        response.setData(AccountDTO.to(savedAccount));
        response.setCode(ResponseCode.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Update Account", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The Account successfully is created"),
            @ApiResponse(code = 404, message = "The Account could not be created")
    })
    @PutMapping
    public ResponseEntity<ResponseDTO<AccountDTO>> updateAccount(@ModelAttribute AccountDTO accountDTO) {
        ResponseDTO<AccountDTO> response = new ResponseDTO<>();
        AccountEntity savedAccount = new AccountEntity();
        try {
            savedAccount = accountService.save(AccountDTO.from(accountDTO));
        } catch (IllegalArgumentException ex) {
            response.setDate(new Date());
            response.setCode(ResponseCode.INTERNAL_ERROR);
            response.setMessage("Account can not be updated;");
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        response.setDate(new Date());
        response.setData(AccountDTO.to(savedAccount));
        response.setCode(ResponseCode.SUCCESS);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Deleting a specific account", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteAccount(@PathVariable Long id) {
        ResponseDTO<String> response = new ResponseDTO<>();
        Optional<AccountEntity> detachedAccount = accountService.getById(id);
        if (detachedAccount.isEmpty()) {
            response.setDate(new Date());
            response.setCode(ResponseCode.NOT_FOUND_CONTENT);
            response.setMessage("Account with id: " + id + ", was not found");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        detachedAccount.ifPresent(accountService::evict);
        response.setDate(new Date());
        response.setData("Account was deleted");
        response.setCode(ResponseCode.DELETED);
        response.setMessage("Account with id: " + id + ", was deleted");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
