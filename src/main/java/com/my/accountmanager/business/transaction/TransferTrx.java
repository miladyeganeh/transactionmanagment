package com.my.accountmanager.business.transaction;

import com.my.accountmanager.business.transaction.validation.aggregator.ValidationAggregator;
import com.my.accountmanager.domain.dto.request.TransactionRequestDTO;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.service.DocumentService;
import com.my.accountmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author M.Yeganeh on 01/06/2020.
 */
@Service
public class TransferTrx extends ProcessTrx {

    private ValidationAggregator validator;
    private TrxValidation trxValidation;

    @Autowired
    public TransferTrx(TransactionService transactionService,
                       DocumentService documentService,
                       @Qualifier(value = "transferValidatorAggregator") ValidationAggregator validator) {
        this.transactionService = transactionService;
        this.documentService = documentService;
        this.validator = validator;
    }

    @Override
    void initiate(TransactionRequestDTO transactionRequestDTO) {
        setTrxValidation(transactionRequestDTO);
    }

    @Override
    @Transactional
    public void doTransaction() {

    }

    @Override
    protected List<TrxValidatorMessages> validate() {
        return validator.aggregate(this.trxValidation);
    }

    private void setTrxValidation(TransactionRequestDTO transactionRequestDTO) {
        this.trxValidation.setSourceAccountNumber(transactionRequestDTO.getSourceAccount().getAccountNumber());
        this.trxValidation.setDestinationAccountNumber(transactionRequestDTO.getDestinationAccount().getAccountNumber());
        this.trxValidation.setAmount(transactionRequestDTO.getAmount());
        this.trxValidation.setTime(transactionRequestDTO.getTime());
    }
}
