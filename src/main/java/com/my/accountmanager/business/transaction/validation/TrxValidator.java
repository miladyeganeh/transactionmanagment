package com.my.accountmanager.business.transaction.validation;

import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;

public interface TrxValidator {
    TrxValidatorMessages validate(TrxValidation validationModel);
}
