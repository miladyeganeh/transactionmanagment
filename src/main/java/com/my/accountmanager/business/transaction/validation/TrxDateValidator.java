package com.my.accountmanager.business.transaction.validation;

import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import org.springframework.stereotype.Component;

@Component("trxDateValidator")
public class TrxDateValidator implements TrxValidator{

    @Override
    public TrxValidatorMessages validate(TrxValidation validationModel) {
        return new TrxValidatorMessages();
    }
}
