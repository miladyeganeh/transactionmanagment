package com.my.accountmanager.business.transaction.validation.aggregator;


import com.my.accountmanager.business.transaction.validation.rule.ValidationRule;
import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component("withdrawValidatorAggregator")
public class WithdrawValidatorAggregator implements ValidationAggregator{
    private final BiConsumer<TrxInfo, TransactionRequestDTO> validationChain;

    @Autowired
    public WithdrawValidatorAggregator(@Qualifier("trxDateValidator") ValidationRule trxDateValidator,
                                       @Qualifier("trxAccountNumberValidator") ValidationRule trxAccountNumberValidator,
                                       @Qualifier("trxDuplicationValidator") ValidationRule trxDuplicationValidator,
                                       @Qualifier("trxCardPassValidator") ValidationRule trxCardValidator) {
        this.validationChain =
                trxDateValidator.performValidation()
                        .andThen(trxAccountNumberValidator.performValidation())
                        .andThen(trxDuplicationValidator.performValidation())
                        .andThen(trxCardValidator.performValidation());
    }

    @Override
    public BiConsumer<TrxInfo, TransactionRequestDTO> getValidationChain() {
        return validationChain;
    }
}
