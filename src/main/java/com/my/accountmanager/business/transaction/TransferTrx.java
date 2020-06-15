package com.my.accountmanager.business.transaction;

import com.my.accountmanager.business.accounting.AccountantService;
import com.my.accountmanager.business.transaction.validation.aggregator.ValidationAggregator;
import com.my.accountmanager.domain.entity.DepositEntity;
import com.my.accountmanager.domain.entity.DocumentEntity;
import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.domain.entity.TransactionRequestEntity;
import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.exception.AccountServiceException;
import com.my.accountmanager.exception.CurrencyServiceException;
import com.my.accountmanager.exception.configuration.TransactionServiceException;
import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import com.my.accountmanager.model.dto.response.TransactionResponseDTO;
import com.my.accountmanager.service.AccountService;
import com.my.accountmanager.service.DocumentService;
import com.my.accountmanager.service.TransactionRequestService;
import com.my.accountmanager.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.util.*;

@Component("transferTrx")
public class TransferTrx extends TrxProcessor {
    private static final Logger logger = LoggerFactory.getLogger(TransferTrx.class);

    private final ValidationAggregator validator;
    private final AccountantService accountantService;
    private final TransactionRequestService trxReqService;

    @Autowired
    public TransferTrx(TransactionService transactionService, DocumentService documentService,
                       @Qualifier(value = "transferValidatorAggregator") ValidationAggregator validator,
                       AccountantService accountantService, TransactionRequestService trxReqService, AccountService accountService) {
        this.transactionService = transactionService;
        this.documentService = documentService;
        this.validator = validator;
        this.accountantService = accountantService;
        this.trxReqService = trxReqService;
    }

    @Override
    public List<String> validate(TrxInfo trxInfo, TransactionRequestDTO trxReq) {
        logger.debug(":::::Start validate transfer transaction");
        return validator.aggregate(trxInfo, trxReq);
    }

    @Override
    public TransactionResponseDTO doTransaction(TransactionRequestDTO trxReq) {
        try {
            return processTransaction(trxReq);
        } catch (TransactionServiceException | AccountServiceException | CurrencyServiceException ex) {
            TransactionRequestEntity requestEntity = trxReqService.saveFailedTransactionRequest(trxReq, Collections.singletonList(ex.getMessage()));
            return TransactionResponseDTO.from(requestEntity);
        } catch (NoSuchElementException | IllegalStateException | OptimisticLockException ex2) {
            throw new TransactionServiceException("transaction cannot be processed");
        }
    }

    @Override
    @Transactional
    public TransactionResponseDTO processTransaction(TransactionRequestDTO trxReq) {
        TransactionRequestEntity requestEntity;
        TrxInfo trxInfo = initiate(trxReq);
        List<String> validateMessages = validate(trxInfo, trxReq);
        if (!validateMessages.isEmpty()) {
            requestEntity = trxReqService.saveFailedTransactionRequest(trxReq, validateMessages);
        } else {
            DocumentEntity documentEntity = accountantService.issueDocument(trxInfo.getSourceAccount(), trxInfo.getDestinationAccount(), trxInfo, trxReq);
            TransactionEntity transaction = createTransaction(trxInfo, documentEntity);
            requestEntity = trxReqService.saveSuccessTransactionRequest(trxReq, transaction);
        }
        return TransactionResponseDTO.from(requestEntity);
    }

    @Override
    public TransactionType getTrxType() {
        return TransactionType.TRANSFER;
    }

    //============================================ Private Method ============================================

    private TransactionEntity createTransaction(TrxInfo trxInfo, DocumentEntity documentEntity) throws RuntimeException {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTerminalID(trxInfo.getTerminalId());
        transactionEntity.setCurrency(trxInfo.getCurrencyInfo().getBaseCurrency());
        transactionEntity.setType(TransactionType.TRANSFER);
        transactionEntity.setTrxDate(new Date());
        transactionEntity.setAmount(trxInfo.getAmount());
        transactionEntity.setSourceAccount(trxInfo.getSourceAccount());
        transactionEntity.setDestinationAccount(trxInfo.getDestinationAccount());
        transactionEntity.setDeposit(getAvailableDeposit(trxInfo.getSourceAccount().getDeposit()));
        transactionEntity.setDocumentEntity(documentEntity);
        transactionEntity = transactionService.save(transactionEntity);
        return transactionEntity;
    }

    private DepositEntity getAvailableDeposit(Set<DepositEntity> depositEntity) {
        return depositEntity
                .stream()
                .filter(DepositEntity::getIsDefault)
                .findFirst()
                .orElse(depositEntity.stream()
                        .findFirst().orElseThrow());
    }
}