package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.model.dto.AccountDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
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
    AccountDTO persist(AccountDTO accountRequestDTO);
    ResponseDTO<AccountDTO> createAccountResponse(AccountDTO accountDTO, ResponseCode code);
}
