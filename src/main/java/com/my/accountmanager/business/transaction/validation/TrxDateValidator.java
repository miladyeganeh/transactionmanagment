package com.my.accountmanager.business.transaction.validation;

import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.utils.DateUtil;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("trxDateValidator")
public class TrxDateValidator implements TrxValidator{

    @Override
    public TrxValidatorMessages validate(TrxValidation validationModel) {
        TrxValidatorMessages trxValidatorMessages = new TrxValidatorMessages();
        long differTime = DateUtil.timeDiffer(new Date(), validationModel.getTime());
        if (differTime > DateUtil.TRANSACTION_WINDOW_TIME){
            trxValidatorMessages.setFailValidation(true);
            trxValidatorMessages.setMessage("Transaction time is invalid.");
        }
        return trxValidatorMessages;
    }
}
