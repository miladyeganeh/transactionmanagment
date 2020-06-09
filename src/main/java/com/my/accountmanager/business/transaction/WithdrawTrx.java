package com.my.accountmanager.business.transaction;

import com.my.accountmanager.business.transaction.validation.aggregator.ValidationAggregator;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.model.dto.TransactionDTO;
import com.my.accountmanager.service.DocumentService;
import com.my.accountmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("withdrawTrx")
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
    public void initiate(TransactionDTO transactionRequestDTO) {
        this.trxValidation.setSourceAccountNumber(transactionRequestDTO.getSourceAccount().getAccountNumber());
    }

    @Override
    public void doTransaction() {

    }

    @Override
    public List<TrxValidatorMessages> validate() {
        return validator.aggregate(this.trxValidation);
    }
}
