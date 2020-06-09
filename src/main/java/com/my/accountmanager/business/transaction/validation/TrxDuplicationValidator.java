package com.my.accountmanager.business.transaction.validation;

import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("trxDuplicationValidator")
public class TrxDuplicationValidator implements TrxValidator {

    private TransactionService transactionService;

    @Autowired
    public TrxDuplicationValidator(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public TrxValidatorMessages validate(TrxValidation validationModel) {
        TrxValidatorMessages trxValidatorMessages = new TrxValidatorMessages();
        Optional<TransactionEntity> duplicatedTrx = transactionService.getByTransactionID(validationModel.getTrxID());
        duplicatedTrx.ifPresent(trx -> {
            trxValidatorMessages.setMessage("Duplicate transaction...");
            trxValidatorMessages.setFailValidation(true);
        });
        return trxValidatorMessages;
    }
}
