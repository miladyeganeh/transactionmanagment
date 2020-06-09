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
import com.my.accountmanager.service.CurrencyService;
import com.my.accountmanager.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountantServiceImpl implements AccountantService {

    private final DocumentService documentService;
    private final AccountService accountService;
    private final CurrencyService currencyService;
    private final ValidationAggregator validator;
    private TrxValidation trxValidation;

    @Autowired
    public AccountantServiceImpl(DocumentService documentService,
                                 AccountService accountService,
                                 CurrencyService currencyService,
                                 @Qualifier("documentIssuanceValidatorAggregator") ValidationAggregator validator) {
        this.documentService = documentService;
        this.accountService = accountService;
        this.currencyService = currencyService;
        this.validator = validator;
    }

    @Override
    public void initiate(TrxValidation trxValidation) {
        this.trxValidation = trxValidation;
    }


    @Override
    public List<TrxValidatorMessages> issueDocument(AccountEntity sourceAccount, AccountEntity destinationAccount, TransactionEntity transactionEntity) {
        List<TrxValidatorMessages> validateMessages = this.validate();
        if (!validateMessages.isEmpty()) {
            return validateMessages;
        }
        this.calculate(sourceAccount, destinationAccount, transactionEntity);
        documentService.createDocument(createDocument(transactionEntity));
        accountService.save(sourceAccount);
        accountService.save(destinationAccount);
        return validateMessages;
    }

    @Override
    public void calculate(AccountEntity sourceAccount, AccountEntity destinationAccount, TransactionEntity transactionEntity) {
        Map<String, Double> rates = this.currencyService.getRate().getRates();
        Double exchangeRate = rates.get(transactionEntity.getCurrency().getName());
        double newAmount = (sourceAccount.getBalance() * exchangeRate) - transactionEntity.getAmount();
        sourceAccount.setBalance(newAmount);
        destinationAccount.setBalance((destinationAccount.getBalance() * exchangeRate) + newAmount);
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
        documentEntity.setBillNumber(documentService.createBillNumber(transactionEntity.getTerminalID(), transactionEntity.getTransactionID()));
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
