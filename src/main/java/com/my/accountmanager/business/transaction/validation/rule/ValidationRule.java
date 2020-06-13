package com.my.accountmanager.business.transaction.validation.rule;

import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;

import java.util.function.BiConsumer;

public interface ValidationRule {
    void validate(TrxInfo trxInfo, TransactionRequestDTO trxReq);

    default BiConsumer<TrxInfo, TransactionRequestDTO> performValidation() {
        return this::validate;
    }
}
