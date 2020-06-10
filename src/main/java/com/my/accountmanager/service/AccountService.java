package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.AccountEntity;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
public interface AccountService {
    Optional<AccountEntity> getByAccountNumber(String accountNumber);
    Optional<AccountEntity> getById(Long id);
    AccountEntity save(AccountEntity accountEntity);
    void evict(AccountEntity accountEntity);
}
