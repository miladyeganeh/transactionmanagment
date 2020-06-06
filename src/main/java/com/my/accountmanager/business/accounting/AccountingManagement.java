package com.my.accountmanager.business.accounting;

import com.my.accountmanager.business.transaction.validation.aggregator.ValidationAggregator;
import com.my.accountmanager.domain.dto.request.TransactionDTO;
import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.entity.DocumentEntity;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.service.DocumentItemService;
import com.my.accountmanager.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountingManagement implements Accountant {

    private DocumentService documentService;
    private DocumentItemService documentItemService;
    private ValidationAggregator validator;
    private TrxValidation trxValidation;

    @Autowired
    public AccountingManagement(DocumentService documentService, DocumentItemService documentItemService) {
        this.documentService = documentService;
        this.documentItemService = documentItemService;
    }

    @Override
    public List<TrxValidatorMessages> issueDocument(AccountEntity account, TransactionDTO transactionDTO) {
        List<TrxValidatorMessages> validateMessages = validate();
        if (!validateMessages.isEmpty())
            return validateMessages;
        documentService.createDocument(new DocumentEntity()); // ToDo Complete
        //save documentItem
        return validateMessages;
    }

    @Override
    public void reversDocument() {

    }

    @Override
    public List<TrxValidatorMessages> validate() {
        return validator.aggregate(this.trxValidation);
    }
}
