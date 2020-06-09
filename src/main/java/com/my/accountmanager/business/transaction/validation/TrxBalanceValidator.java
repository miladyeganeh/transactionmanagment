package com.my.accountmanager.business.transaction.validation;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("trxBalanceValidator")
public class TrxBalanceValidator implements TrxValidator{

    private AccountService accountService;

    @Autowired
    public TrxBalanceValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public TrxValidatorMessages validate(TrxValidation validationModel) {
        TrxValidatorMessages trxValidatorMessages = new TrxValidatorMessages();
        Optional<AccountEntity> accountEntity = accountService.getByAccountNumber(validationModel.getSourceAccountNumber());
        accountEntity.ifPresent(account -> {
            if ((account.getBalance() - validationModel.getAmount()) < 0) {
                trxValidatorMessages.setMessage("Account has not enough balance");
                trxValidatorMessages.setFailValidation(true);
            }

        });
        return trxValidatorMessages;
    }
}
