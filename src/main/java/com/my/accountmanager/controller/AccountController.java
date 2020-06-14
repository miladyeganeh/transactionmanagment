package com.my.accountmanager.controller;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.model.dto.request.AccountRequestDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.withrel.AccountResponseDTO;
import com.my.accountmanager.model.enums.ResponseCode;
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
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ApiOperation(value = "View a specific Account Details", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved account details"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/{accountNumber}")
    public ResponseEntity<ResponseDTO<AccountResponseDTO>> getAccount(@PathVariable String accountNumber) {
        Optional<AccountEntity> accountEntity = this.accountService.findByAccountNumber(accountNumber);
        return accountEntity
                .map(account -> ResponseEntity.ok().body(accountService.createAccountResponse(AccountResponseDTO.form(account), ResponseCode.FOUND_CONTENT)))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }

    @ApiOperation(value = "Create Account", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Account successfully is created"),
            @ApiResponse(code = 500, message = "The Account could not be created")
    })
    @PostMapping
    public ResponseEntity<ResponseDTO<AccountResponseDTO>> createAccount(@RequestBody AccountRequestDTO accountDTO) {
        AccountResponseDTO accountResponseDTO = this.accountService.create(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.createAccountResponse(accountResponseDTO, ResponseCode.SUCCESS));
    }

    @ApiOperation(value = "Update Account", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Account successfully is created"),
            @ApiResponse(code = 404, message = "The Account could not be created")
    })
    @PutMapping
    public ResponseEntity<ResponseDTO<AccountResponseDTO>> updateAccount(@RequestBody AccountRequestDTO accountDTO) {
        AccountResponseDTO accountResponseDTO = this.accountService.persist(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.createAccountResponse(accountResponseDTO, ResponseCode.SUCCESS));
    }

    @ApiOperation(value = "Deleting a specific account", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        Optional<AccountEntity> detachedAccount = this.accountService.findById(id);
        detachedAccount.ifPresent(accountService::delete);
        return detachedAccount
                .map(acc -> ResponseEntity.ok().<Void>build())
                .orElse(ResponseEntity.notFound().build());
    }
}