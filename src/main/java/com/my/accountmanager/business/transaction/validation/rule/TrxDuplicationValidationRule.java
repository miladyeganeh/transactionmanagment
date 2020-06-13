package com.my.accountmanager.business.transaction.validation.rule;

import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import com.my.accountmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("trxDuplicationValidator")
public class TrxDuplicationValidationRule implements ValidationRule {

    private final TransactionService transactionService;

    @Autowired
    public TrxDuplicationValidationRule(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void validate(TrxInfo trxInfo, TransactionRequestDTO trxReq) {
        Optional<TransactionEntity> duplicatedTrx = transactionService.findByTransactionID(trxInfo.getTrxID());
        duplicatedTrx.ifPresent(trx -> trxInfo.addValidationError("Duplicate transaction..."));
    }
}
