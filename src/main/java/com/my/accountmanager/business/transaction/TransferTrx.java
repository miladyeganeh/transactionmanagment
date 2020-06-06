package com.my.accountmanager.business.transaction;

import com.my.accountmanager.business.accounting.Accountant;
import com.my.accountmanager.business.transaction.validation.aggregator.ValidationAggregator;
import com.my.accountmanager.domain.dto.request.TransactionDTO;
import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.service.DocumentService;
import com.my.accountmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author M.Yeganeh on 01/06/2020.
 */
@Service
public class TransferTrx extends ProcessTrx {

    private final ValidationAggregator validator;
    private final Accountant accountant;
    private TrxValidation trxValidation;
    private TransactionDTO transactionDTO;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TransferTrx(TransactionService transactionService,
                       DocumentService documentService,
                       @Qualifier(value = "transferValidatorAggregator") ValidationAggregator validator,
                       Accountant accountant) {
        this.transactionService = transactionService;
        this.documentService = documentService;
        this.validator = validator;
        this.accountant = accountant;
    }

    @Override
    void initiate(TransactionDTO transactionRequest) {
        this.transactionDTO = transactionRequest;
        setTrxValidation(transactionRequest);
    }

    @Override
    public void doTransaction() {
        EntityTransaction trx = null;
        try {
            trx = entityManager.getTransaction();
            processTransaction(trx);
        }catch ( IllegalStateException | OptimisticLockException  exception) {
            assert trx != null;
            trx.rollback();
            processTransaction(trx);
        }
    }

    @Override
    protected List<TrxValidatorMessages> validate() {
        return validator.aggregate(this.trxValidation);
    }

    //============================================ Private Method ============================================
    private void processTransaction(EntityTransaction trx) {
        AccountEntity account = entityManager.find(AccountEntity.class, Long.class, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        trx.begin();
        TransactionDTO transaction = transactionService.createTransaction(transactionDTO);
        accountant.issueDocument(account, transaction);
        trx.commit();
    }

    private void setTrxValidation(TransactionDTO transactionRequestDTO) {
        this.trxValidation.setSourceAccountNumber(transactionRequestDTO.getSourceAccount().getAccountNumber());
        this.trxValidation.setDestinationAccountNumber(transactionRequestDTO.getDestinationAccount().getAccountNumber());
        this.trxValidation.setAmount(transactionRequestDTO.getAmount());
        this.trxValidation.setTime(transactionRequestDTO.getTime());
    }
}
