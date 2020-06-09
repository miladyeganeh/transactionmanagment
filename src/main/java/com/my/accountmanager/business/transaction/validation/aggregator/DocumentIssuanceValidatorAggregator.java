package com.my.accountmanager.business.transaction.validation.aggregator;

import com.my.accountmanager.business.transaction.validation.TrxValidator;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("documentIssuanceValidatorAggregator")
public class DocumentIssuanceValidatorAggregator implements ValidationAggregator {

    private TrxValidator trxBalanceValidator;

    public DocumentIssuanceValidatorAggregator(@Qualifier("trxBalanceValidator") TrxValidator trxBalanceValidator) {
        this.trxBalanceValidator = trxBalanceValidator;
    }

    @Override
    public List<TrxValidatorMessages> aggregate(TrxValidation trxValidation) {
        List<TrxValidatorMessages> trxValidatorMessages = new ArrayList<>();
        TrxValidatorMessages trxBalanceValidator = this.trxBalanceValidator.validate(trxValidation);
        if (trxBalanceValidator.getFailValidation()) {
            trxValidatorMessages.add(trxBalanceValidator);
        }
        return trxValidatorMessages;
    }
}
