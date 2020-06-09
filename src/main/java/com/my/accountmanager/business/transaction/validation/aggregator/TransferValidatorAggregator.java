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
        TrxValidatorMessages trxDateValidator = this.trxDateValidator.validate(trxValidation);
        if (trxDateValidator.getFailValidation()) {
            trxValidatorMessages.add(trxDateValidator);
        }
        TrxValidatorMessages trxFromAccountValidator = this.trxFromAccountValidator.validate(trxValidation);
        if (trxFromAccountValidator.getFailValidation()) {
            trxValidatorMessages.add(trxFromAccountValidator);
        }
        TrxValidatorMessages trxDuplicationValidator = this.trxDuplicationValidator.validate(trxValidation);
        if (trxDuplicationValidator.getFailValidation()) {
            trxValidatorMessages.add(trxDuplicationValidator);
        }
        TrxValidatorMessages trxCardValidator = this.trxCardValidator.validate(trxValidation);
        if (trxCardValidator.getFailValidation()) {
            trxValidatorMessages.add(trxCardValidator);
        }
        return trxValidatorMessages;
    }
}
