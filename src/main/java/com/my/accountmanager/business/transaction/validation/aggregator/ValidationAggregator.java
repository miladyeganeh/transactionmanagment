package com.my.accountmanager.business.transaction.validation.aggregator;

import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;

import java.util.List;

public interface ValidationAggregator {
    List<TrxValidatorMessages> aggregate (TrxValidation trxValidation);
}
