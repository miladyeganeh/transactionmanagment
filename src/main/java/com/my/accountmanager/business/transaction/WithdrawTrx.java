package com.my.accountmanager.business.transaction;

import com.my.accountmanager.business.transaction.validation.aggregator.ValidationAggregator;
import com.my.accountmanager.domain.dto.request.TransactionDTO;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.service.DocumentService;
import com.my.accountmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class WithdrawTrx extends ProcessTrx {

    private ValidationAggregator validator;
    private TrxValidation trxValidation;

    @Autowired
    public WithdrawTrx(TransactionService transactionService,
                       DocumentService documentService,
                       @Qualifier(value = "withdrawValidatorAggregator") ValidationAggregator validator) {
        this.transactionService = transactionService;
        this.documentService = documentService;
        this.validator = validator;
    }

    @Override
    void initiate(TransactionDTO transactionRequestDTO) {
        this.trxValidation.setSourceAccountNumber(transactionRequestDTO.getSourceAccount().getAccountNumber());
    }

    @Override
    void doTransaction() {

    }

    @Override
    protected List<TrxValidatorMessages> validate() {
        return validator.aggregate(this.trxValidation);
    }
}