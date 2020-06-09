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

import javax.persistence.EntityTransaction;
import java.util.List;

@Component("depositTrx")
public class DepositTrx extends ProcessTrx {

    private ValidationAggregator validator;
    private TrxValidation trxValidation;

    @Autowired
    public DepositTrx(TransactionService transactionService,
                      DocumentService documentService,
                      @Qualifier(value = "depositValidatorAggregator") ValidationAggregator validator) {
        this.transactionService = transactionService;
        this.documentService = documentService;
        this.validator = validator;
    }

    @Override
    public void initiate(TransactionDTO transactionRequestDTO) {

    }

    @Override
    public void doTransaction() {

    }

    @Override
    protected void createTransaction(EntityTransaction trx) {

    }

    @Override
    public List<TrxValidatorMessages> validate() {
        return validator.aggregate(this.trxValidation);
    }
}
