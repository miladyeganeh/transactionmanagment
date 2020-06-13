package com.my.accountmanager.business.transaction;

import com.my.accountmanager.business.transaction.validation.aggregator.ValidationAggregator;
import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import com.my.accountmanager.model.dto.response.TransactionResponseDTO;
import com.my.accountmanager.service.DocumentService;
import com.my.accountmanager.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("withdrawTrx")
public class WithdrawTrx extends TrxProcessor {
    private static final Logger logger = LoggerFactory.getLogger(WithdrawTrx.class);

    private final ValidationAggregator validator;

    @Autowired
    public WithdrawTrx(TransactionService transactionService,
                       DocumentService documentService,
                       @Qualifier(value = "withdrawValidatorAggregator") ValidationAggregator validator) {
        this.transactionService = transactionService;
        this.documentService = documentService;
        this.validator = validator;
    }

    @Override
    public TransactionResponseDTO doTransaction(TransactionRequestDTO trxReq) {
        return null;
    }

    @Override
    protected TransactionResponseDTO processTransaction(TransactionRequestDTO trxReq) {
        return null;
    }

    @Override
    public TransactionType getTrxType() {
        return TransactionType.WITHDRAW;
    }

    @Override
    public List<String> validate(TrxInfo trxInfo, TransactionRequestDTO trxReq) {
        return validator.aggregate(trxInfo, trxReq);
    }

}
