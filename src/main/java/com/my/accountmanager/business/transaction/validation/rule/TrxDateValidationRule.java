package com.my.accountmanager.business.transaction.validation.rule;

import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component("trxDateValidator")
public class TrxDateValidationRule implements ValidationRule {

    @Override
    public void validate(TrxInfo trxInfo, TransactionRequestDTO trxReq) {
        //todo add date validation with config
    }
}
