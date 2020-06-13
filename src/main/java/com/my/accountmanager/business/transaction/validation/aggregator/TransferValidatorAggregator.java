package com.my.accountmanager.business.transaction.validation.aggregator;

import com.my.accountmanager.business.transaction.validation.rule.ValidationRule;
import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component("transferValidatorAggregator")
public class TransferValidatorAggregator implements ValidationAggregator {

    private final BiConsumer<TrxInfo, TransactionRequestDTO> validationChain;

    @Autowired
    public TransferValidatorAggregator(@Qualifier("trxDateValidator") ValidationRule trxDateValidator,
                                       @Qualifier("trxAccountNumberValidator") ValidationRule trxAccountNumberValidator,
                                       @Qualifier("trxDuplicationValidator") ValidationRule trxDuplicationValidator) {
        this.validationChain =
                trxDateValidator.performValidation()
                        .andThen(trxAccountNumberValidator.performValidation())
                        .andThen(trxDuplicationValidator.performValidation());
    }

    @Override
    public BiConsumer<TrxInfo, TransactionRequestDTO> getValidationChain() {
        return validationChain;
    }
}
