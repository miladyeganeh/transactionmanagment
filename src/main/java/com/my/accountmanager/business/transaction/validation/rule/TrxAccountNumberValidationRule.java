package com.my.accountmanager.business.transaction.validation.rule;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import com.my.accountmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.function.Consumer;

@Component("trxAccountNumberValidator")
public class TrxAccountNumberValidationRule implements ValidationRule {

    private final AccountService accountService;

    @Autowired
    public TrxAccountNumberValidationRule(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void validate(TrxInfo trxInfo, TransactionRequestDTO trxReq) {
        if (!StringUtils.isEmpty(trxReq.getSourceAccountNumber())) {
            validateSourceAccountNumber(trxInfo, trxReq.getSourceAccountNumber(), trxInfo::setSourceAccount);
        }
        if (!StringUtils.isEmpty(trxReq.getDestinationAccountNumber())) {
            validateDestinationAccountNumber(trxInfo, trxReq.getDestinationAccountNumber(), trxInfo::setDestinationAccount);
        }
    }

    private void validateSourceAccountNumber(TrxInfo trxInfo, String accountNumber, Consumer<AccountEntity> successHandler) {
        Optional<AccountEntity> accountEntity = accountService.findActiveAccountByAccountNumber(accountNumber);
        accountEntity.ifPresentOrElse(successHandler, () -> trxInfo.addValidationError("Source Account not found"));
    }

    private void validateDestinationAccountNumber(TrxInfo trxInfo, String accountNumber, Consumer<AccountEntity> successHandler) {
        Optional<AccountEntity> accountEntity = accountService.findActiveAccountByAccountNumber(accountNumber);
        accountEntity.ifPresentOrElse(successHandler, () -> trxInfo.addValidationError("Destination Account not found"));
    }
}
