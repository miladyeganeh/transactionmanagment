package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.withrel.AccountResponseDTO;
import com.my.accountmanager.model.enums.ResponseCode;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
public interface AccountService extends BaseCrudService<AccountEntity> {
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
    Optional<AccountEntity> findById(Long id);
    AccountEntity save(AccountEntity accountEntity);
    Optional<AccountEntity> findActiveAccountByAccountNumber(String accountNumber);
    AccountResponseDTO persist(AccountResponseDTO accountRequestDTO);
    ResponseDTO<AccountResponseDTO> createAccountResponse(AccountResponseDTO accountDTO, ResponseCode code);
}
