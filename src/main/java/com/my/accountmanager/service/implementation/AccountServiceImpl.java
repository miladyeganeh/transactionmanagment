package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.AccountEntity;
import com.my.accountmanager.repository.AccountRepository;
import com.my.accountmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author M.Yeganeh on 31/05/2020.
 */

@Service
public class AccountServiceImpl extends BaseServiceImpl<AccountEntity, AccountRepository> implements AccountService {
    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        super(repository);
    }
}
