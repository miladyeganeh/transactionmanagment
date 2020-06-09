package com.my.accountmanager.business.transaction.validation.aggregator;

import com.my.accountmanager.business.transaction.validation.TrxValidator;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("transferValidatorAggregator")
public class TransferValidatorAggregator implements ValidationAggregator {


    private final TrxValidator trxDateValidator;
    private final TrxValidator trxFromAccountValidator;
    private final TrxValidator trxDuplicationValidator;
    private final TrxValidator trxCardValidator;

    @Autowired
    public TransferValidatorAggregator(@Qualifier("trxDateValidator") TrxValidator trxDateValidator,
                                       @Qualifier("trxFromAccountValidator") TrxValidator trxFromAccountValidator,
                                       @Qualifier("trxDuplicationValidator") TrxValidator trxDuplicationValidator,
                                       @Qualifier("trxCardPassValidator") TrxValidator trxCardValidator) {
        this.trxDateValidator = trxDateValidator;
        this.trxFromAccountValidator = trxFromAccountValidator;
        this.trxDuplicationValidator = trxDuplicationValidator;
        this.trxCardValidator = trxCardValidator;
    }

    @Override
    public List<TrxValidatorMessages> aggregate(TrxValidation trxValidation) {
        List<TrxValidatorMessages> trxValidatorMessages = new ArrayList<>();
        TrxValidatorMessages validDate = trxDateValidator.validate(trxValidation);
        if (validDate.getFailValidation()) {
            trxValidatorMessages.add(validDate);
        }
        TrxValidatorMessages validateFrom = trxFromAccountValidator.validate(trxValidation);
        if (validateFrom.getFailValidation()) {
            trxValidatorMessages.add(validateFrom);
        }
        TrxValidatorMessages duplicationValidator = trxDuplicationValidator.validate(trxValidation);
        if (duplicationValidator.getFailValidation()) {
            trxValidatorMessages.add(duplicationValidator);
        }
        TrxValidatorMessages cardValidator = trxCardValidator.validate(trxValidation);
        if (cardValidator.getFailValidation()) {
            trxValidatorMessages.add(cardValidator);
        }
        return trxValidatorMessages;
    }
}
