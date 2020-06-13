package com.my.accountmanager.business.transaction.validation.rule;

import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import org.springframework.stereotype.Component;

@Component("trxTypeValidator")
public class TrxTypeValidationRule implements ValidationRule {

    @Override
    public void validate(TrxInfo trxInfo, TransactionRequestDTO trxReq) {
        TransactionType trxType = TransactionType.getByValue(trxReq.getTransactionType());
        if (trxType == null) {
            trxInfo.addValidationError("Unsupported Transaction...");
        } else {
            trxInfo.setTrxType(trxType);
        }
    }
}
