package com.my.accountmanager.business.accounting;

import com.my.accountmanager.business.transaction.validation.aggregator.ValidationAggregator;
import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.entity.DocumentEntity;
import com.my.accountmanager.domain.entity.DocumentItemEntity;
import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.domain.enums.DocumentStatus;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.service.AccountService;
import com.my.accountmanager.service.DocumentItemService;
import com.my.accountmanager.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountingManagement implements Accountant {

    private DocumentService documentService;
    private DocumentItemService documentItemService;
    private ValidationAggregator validator;
    private TrxValidation trxValidation;

    @Autowired
    public AccountingManagement(DocumentService documentService,
                                DocumentItemService documentItemService,
                                AccountService accountService) {
        this.documentService = documentService;
        this.documentItemService = documentItemService;
    }

    @Override
    public List<TrxValidatorMessages> issueDocument(AccountEntity sourceAccount, AccountEntity destinationAccount, TransactionEntity transactionEntity) {
        List<TrxValidatorMessages> validateMessages = validate();
        if (!validateMessages.isEmpty())
            return validateMessages;
        documentService.createDocument(createDocument(transactionEntity));
        return validateMessages;
    }

    @Override
    public void calculate(AccountEntity sourceAccount, AccountEntity destinationAccount, TransactionEntity transactionEntity) {
        Double newAmount = sourceAccount.getBalance() - transactionEntity.getAmount();
        sourceAccount.setBalance(newAmount);
        destinationAccount.setBalance(destinationAccount.getBalance() + newAmount);
    }

    @Override
    public void reversDocument() {

    }

    @Override
    public List<TrxValidatorMessages> validate() {
        return validator.aggregate(this.trxValidation);
    }

    private DocumentEntity createDocument(TransactionEntity transactionEntity) {
        String comment = documentService.createDocumentComment(transactionEntity.getTransactionID(), transactionEntity.getType());
        DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setBillNumber("billNumber"); // todo create bill number
        documentEntity.setComment(comment);
        documentEntity.setIssuanceDate(new Date());
        documentEntity.setTotalAmount(transactionEntity.getAmount());
        documentEntity.setStatus(DocumentStatus.ISSUED);
        documentEntity.setDocumentItems(createDocumentItemEntity(transactionEntity, documentEntity));
        return documentEntity;
    }

    private Set<DocumentItemEntity> createDocumentItemEntity(TransactionEntity transactionEntity, DocumentEntity documentEntity) {
        Set<DocumentItemEntity> documentItemEntities = new HashSet<>();
        DocumentItemEntity documentItemEntity = new DocumentItemEntity();
        documentItemEntity.setDocument(documentEntity);
        documentItemEntity.setAmount(transactionEntity.getAmount());
        documentItemEntity.setIssuanceDate(new Date());
        documentEntity.setComment(documentEntity.getComment());
        documentItemEntities.add(documentItemEntity);
        return documentItemEntities;
    }
}
