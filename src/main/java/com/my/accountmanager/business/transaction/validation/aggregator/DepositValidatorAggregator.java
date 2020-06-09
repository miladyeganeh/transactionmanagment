package com.my.accountmanager.business.transaction.validation.aggregator;

import com.my.accountmanager.business.transaction.validation.TrxValidator;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("depositValidatorAggregator")
public class DepositValidatorAggregator implements ValidationAggregator{

    private final TrxValidator trxDateValidator;
    private final TrxValidator trxFromAccountValidator;
    private final TrxValidator trxDuplicationValidator;

    @Autowired
    public DepositValidatorAggregator(@Qualifier("trxDateValidator") TrxValidator trxDateValidator,
                                      @Qualifier("trxFromAccountValidator") TrxValidator trxFromAccountValidator,
                                      @Qualifier("trxDuplicationValidator") TrxValidator trxDuplicationValidator) {
        this.trxDateValidator = trxDateValidator;
        this.trxFromAccountValidator = trxFromAccountValidator;
        this.trxDuplicationValidator = trxDuplicationValidator;
    }

    @Override
    public List<TrxValidatorMessages> aggregate(TrxValidation trxValidation) {
        List<TrxValidatorMessages> trxValidatorMessages = new ArrayList<>();
        TrxValidatorMessages dateValidator = this.trxDateValidator.validate(trxValidation);
        if (dateValidator.getFailValidation()) {
            trxValidatorMessages.add(dateValidator);
        }
        TrxValidatorMessages trxDuplicationValidator = this.trxDuplicationValidator.validate(trxValidation);
        if (trxDuplicationValidator.getFailValidation()) {
            trxValidatorMessages.add(trxDuplicationValidator);
        }
        TrxValidatorMessages fromAccountValidator = this.trxFromAccountValidator.validate(trxValidation);
        if (fromAccountValidator.getFailValidation()) {
            trxValidatorMessages.add(fromAccountValidator);
        }
        return trxValidatorMessages;
    }
}
