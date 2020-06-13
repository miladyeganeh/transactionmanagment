package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.enums.AccountStatus;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.withrel.AccountResponseDTO;
import com.my.accountmanager.model.enums.ResponseCode;
import com.my.accountmanager.repository.AccountRepository;
import com.my.accountmanager.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */

@Service
public class AccountServiceImpl extends BaseCrudServiceImpl<AccountEntity, AccountRepository> implements AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        super(accountRepository);
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<AccountEntity> findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Optional<AccountEntity> findActiveAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumberAndIsActiveAndStatus(accountNumber, true, AccountStatus.ACTIVE);
    }

    @Override
    public AccountResponseDTO persist(AccountResponseDTO accountDTO) {
        //todo set currency, customer, deposit and cards
        AccountEntity savedAccountEntity = save(AccountResponseDTO.to(accountDTO));
        return AccountResponseDTO.form(savedAccountEntity);
    }

    @Override
    public ResponseDTO<AccountResponseDTO> createAccountResponse(AccountResponseDTO accountDTO, ResponseCode code) {
        return ResponseDTO.<AccountResponseDTO>builder()
                .withData(accountDTO)
                .withDate(new Date())
                .withCode(code)
                .build();
    }
}
