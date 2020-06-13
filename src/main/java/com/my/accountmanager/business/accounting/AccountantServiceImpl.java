package com.my.accountmanager.business.accounting;

import com.my.accountmanager.business.transaction.validation.aggregator.ValidationAggregator;
import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.entity.DocumentEntity;
import com.my.accountmanager.domain.entity.DocumentItemEntity;
import com.my.accountmanager.domain.enums.DocumentStatus;
import com.my.accountmanager.exception.AccountServiceException;
import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.CurrencyInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import com.my.accountmanager.service.AccountService;
import com.my.accountmanager.service.DocumentItemService;
import com.my.accountmanager.service.DocumentService;
import com.my.accountmanager.utils.GlobalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class AccountantServiceImpl implements AccountantService {
    private static final Logger logger = LoggerFactory.getLogger(AccountantServiceImpl.class);

    private final DocumentService documentService;
    private final AccountService accountService;
    private final ValidationAggregator validator;
    private final DocumentItemService documentItemService;

    @Autowired
    public AccountantServiceImpl(DocumentService documentService,
                                 AccountService accountService,
                                 @Qualifier("documentIssuanceValidatorAggregator") ValidationAggregator validator,
                                 DocumentItemService documentItemService) {
        this.documentService = documentService;
        this.accountService = accountService;
        this.validator = validator;
        this.documentItemService = documentItemService;
    }


    @Override
    public DocumentEntity issueDocument(AccountEntity sourceAccount, AccountEntity destinationAccount, TrxInfo trxInfo, TransactionRequestDTO trxReq) {
        List<String> validateMessages = this.validate(trxInfo, trxReq);
        if (!validateMessages.isEmpty()) {
            throw new AccountServiceException(String.join(GlobalConstant.MESSAGE_SEPARATOR, validateMessages));
        }
        DocumentEntity document = createDocument(trxInfo);
        document.getDocumentItems().add(processAccountAmounts(sourceAccount, trxInfo.getAmount(), true, document, trxInfo.getCurrencyInfo()));
        document.getDocumentItems().add(processAccountAmounts(destinationAccount, trxInfo.getAmount(), false, document, trxInfo.getCurrencyInfo()));
        persist(sourceAccount, destinationAccount, document);
        return document;
    }

    private List<String> validate(TrxInfo trxInfo, TransactionRequestDTO trxReq) {
        return validator.aggregate(trxInfo, trxReq);
    }

    private DocumentEntity createDocument(TrxInfo trxInfo) {
        String comment = documentService.createDocumentComment(trxInfo.getTrxID(), trxInfo.getTrxType());
        DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setBillNumber(documentService.createBillNumber(trxInfo.getTerminalId(), trxInfo.getTrxID()));
        documentEntity.setComment(comment);
        documentEntity.setIssuanceDate(new Date());
        documentEntity.setTotalAmount(trxInfo.getAmount());
        documentEntity.setStatus(DocumentStatus.ISSUED);
        documentEntity.setDocumentItems(new HashSet<>());
        documentEntity.setCurrency(trxInfo.getCurrencyInfo().getBaseCurrency());
        return documentEntity;
    }

    private DocumentItemEntity createDocumentItemEntity(AccountEntity accountEntity, Double amount, Boolean isDebtor, DocumentEntity documentEntity, Double exchangeRate) {
        DocumentItemEntity documentItemEntity = new DocumentItemEntity();
        documentItemEntity.setDocument(documentEntity);
        documentItemEntity.setAmount(amount);
        documentItemEntity.setIssuanceDate(new Date());
        documentItemEntity.setAccount(accountEntity);
        documentItemEntity.setCurrency(accountEntity.getCurrency());
        documentItemEntity.setDebtor(isDebtor);
        documentItemEntity.setComment(documentEntity.getComment());
        documentItemEntity.setExchangeRate(exchangeRate);
        return documentItemEntity;
    }

    private DocumentItemEntity processAccountAmounts(AccountEntity accountEntity, Double amount, Boolean isDebtor, DocumentEntity documentEntity, CurrencyInfo currencyInfo) {
        Double exchangeRate = 1D;
        if (!currencyInfo.getBaseCurrency().getCode().equals(accountEntity.getCurrency().getCode())) {
            exchangeRate = currencyInfo.getExchangeRate(accountEntity.getCurrency().getCode());
        }
        double exchangeAmount = amount * exchangeRate;
        double newBalance = accountEntity.getBalance() + ((isDebtor ? -1 : 1) * exchangeAmount);
        accountEntity.setBalance(newBalance);
        return createDocumentItemEntity(accountEntity, amount, isDebtor, documentEntity, exchangeRate);
    }

    private void persist(AccountEntity sourceAccount, AccountEntity destinationAccount, DocumentEntity documentEntity) {
        accountService.update(sourceAccount);
        accountService.update(destinationAccount);
        documentService.save(documentEntity);
        documentEntity.getDocumentItems().forEach(documentItemService::save);
    }
}
