package com.my.accountmanager.business.transaction.validation.aggregator;

import com.my.accountmanager.business.transaction.validation.TrxDateValidator;
import com.my.accountmanager.business.transaction.validation.TrxDuplicationValidator;
import com.my.accountmanager.business.transaction.validation.TrxFromAccountValidator;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("transferValidatorAggregator")
public class TransferValidatorAggregator implements ValidationAggregator {

    private final TrxDateValidator trxDateValidator;
    private final TrxFromAccountValidator trxFromAccountValidator;
    private final TrxDuplicationValidator trxDuplicationValidator;

    @Autowired
    public TransferValidatorAggregator(TrxDateValidator trxDateValidator,
                                       TrxFromAccountValidator trxFromAccountValidator,
                                       TrxDuplicationValidator trxDuplicationValidator) {
        this.trxDateValidator = trxDateValidator;
        this.trxFromAccountValidator = trxFromAccountValidator;
        this.trxDuplicationValidator = trxDuplicationValidator;
    }

    @Override
    public List<TrxValidatorMessages> aggregate(TrxValidation trxValidation) {
        List<TrxValidatorMessages> trxValidatorMessages = new ArrayList<>();
        trxValidatorMessages.add(trxDateValidator.validate(trxValidation));
        trxValidatorMessages.add(trxFromAccountValidator.validate(trxValidation));
        trxValidatorMessages.add(trxDuplicationValidator.validate(trxValidation));

        return trxValidatorMessages;
    }
}
