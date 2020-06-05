package com.my.accountmanager.business.transaction.validation.aggregator;


import com.my.accountmanager.business.transaction.validation.TrxDuplicationValidator;
import com.my.accountmanager.business.transaction.validation.TrxFromAccountValidator;
import com.my.accountmanager.business.transaction.validation.TrxDateValidator;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("withdrawValidatorAggregator")
public class WithdrawValidatorAggregator implements ValidationAggregator{

    private AccountService accountService;

    @Autowired
    public WithdrawValidatorAggregator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public List<TrxValidatorMessages> aggregate(TrxValidation trxValidation) {
        List<TrxValidatorMessages> trxValidatorMessages = new ArrayList<>();
        trxValidatorMessages.add(new TrxDateValidator().validate(trxValidation));
        trxValidatorMessages.add(new TrxFromAccountValidator(this.accountService).validate(trxValidation));
        trxValidatorMessages.add(new TrxDuplicationValidator().validate(trxValidation));

        return trxValidatorMessages;
    }
}
