package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.model.dto.request.AccountRequestDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.withrel.AccountResponseDTO;
import com.my.accountmanager.model.enums.ResponseCode;

import java.util.Optional;

public interface AccountService extends BaseCrudService<AccountEntity> {
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
    Optional<AccountEntity> findById(Long id);
    AccountResponseDTO create(AccountRequestDTO accountDTO);
    Optional<AccountEntity> findActiveAccountByAccountNumber(String accountNumber);
    AccountResponseDTO persist(AccountRequestDTO accountRequestDTO);
    ResponseDTO<AccountResponseDTO> createAccountResponse(AccountResponseDTO accountDTO, ResponseCode code);
}
