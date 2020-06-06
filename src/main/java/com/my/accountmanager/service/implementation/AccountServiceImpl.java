package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.repository.AccountRepository;
import com.my.accountmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */

@Service
public class AccountServiceImpl extends BaseCrudServiceImpl<AccountEntity, AccountRepository> implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        super(accountRepository);
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<AccountEntity> getByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
