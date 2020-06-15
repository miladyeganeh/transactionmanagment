package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.entity.CurrencyEntity;
import com.my.accountmanager.domain.enums.AccountStatus;
import com.my.accountmanager.exception.AccountServiceException;
import com.my.accountmanager.model.dto.request.AccountRequestDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.withrel.AccountResponseDTO;
import com.my.accountmanager.model.enums.ResponseCode;
import com.my.accountmanager.repository.AccountRepository;
import com.my.accountmanager.service.AccountService;
import com.my.accountmanager.service.CardService;
import com.my.accountmanager.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AccountServiceImpl extends BaseCrudServiceImpl<AccountEntity, AccountRepository> implements AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final CurrencyService currencyService;
private final CardService cardService;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, CurrencyService currencyService, CardService cardService) {
        super(accountRepository);
        this.currencyService = currencyService;
        this.cardService = cardService;
    }

    @Override
    public Optional<AccountEntity> findByAccountNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber);
    }

    @Override
    public Optional<AccountEntity> findActiveAccountByAccountNumber(String accountNumber) {
        return repository.findByAccountNumberAndIsActiveAndStatus(accountNumber, true, AccountStatus.ACTIVE);
    }

    @Override
    public AccountResponseDTO create(AccountRequestDTO accountDTO) {
        AccountEntity accountEntity = AccountRequestDTO.to(accountDTO);
        accountEntity.getDeposit().forEach(depositEntity -> depositEntity.setAccount(accountEntity));
        accountEntity.getCards().forEach(cardEntity -> cardEntity.setAccount(accountEntity));
        accountEntity.getCards().forEach(cardEntity -> cardEntity.setCustomer(accountEntity.getCustomer()));
        Optional<CurrencyEntity> currency = this.currencyService.findByCurrencyByCode(accountEntity.getCurrency().getCode());
        currency.ifPresent(accountEntity::setCurrency);
        AccountEntity savedAccountEntity = save(accountEntity);
        return AccountResponseDTO.form(savedAccountEntity);
    }

    @Override
    public AccountResponseDTO persist(AccountRequestDTO accountDTO) {
        Optional<AccountEntity> accountEntity = findById(accountDTO.getId());
        if (accountEntity.isPresent()){
            AccountEntity account = accountEntity.get();
            account.setIsActive(accountDTO.getIsActive());
            account.setStatus(AccountStatus.getByValue(accountDTO.getStatus()));
            Optional<CurrencyEntity> currency = this.currencyService.findByCurrencyByCode(accountDTO.getCurrency().getCode());
            currency.ifPresent(account::setCurrency);
            AccountEntity savedAccount = save(account);
            return AccountResponseDTO.form(savedAccount);
        }
        throw new AccountServiceException("Account not found.");
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
