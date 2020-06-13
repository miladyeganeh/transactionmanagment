package com.my.accountmanager.business.transaction.validation.aggregator;

import com.my.accountmanager.business.transaction.validation.rule.ValidationRule;
import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component("documentIssuanceValidatorAggregator")
public class DocumentIssuanceValidatorAggregator implements ValidationAggregator {

    private final BiConsumer<TrxInfo, TransactionRequestDTO> validationChain;

    public DocumentIssuanceValidatorAggregator(
            @Qualifier("trxBalanceValidator") ValidationRule trxBalanceValidator,
            @Qualifier("trxCurrencyValidator") ValidationRule trxCurrencyValidator
    ) {
        this.validationChain =
                trxCurrencyValidator.performValidation()
                        .andThen(trxBalanceValidator.performValidation());
    }

    @Override
    public BiConsumer<TrxInfo, TransactionRequestDTO> getValidationChain() {
        return validationChain;
    }
}
