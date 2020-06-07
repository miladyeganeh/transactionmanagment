package com.my.accountmanager.business.transaction;

import com.my.accountmanager.business.accounting.Accountant;
import com.my.accountmanager.business.transaction.validation.aggregator.ValidationAggregator;
import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.entity.DepositEntity;
import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import com.my.accountmanager.service.AccountService;
import com.my.accountmanager.service.DepositService;
import com.my.accountmanager.service.DocumentService;
import com.my.accountmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

/**
 * @author M.Yeganeh on 01/06/2020.
 */
@Component("transferTrx")
public class TransferTrx extends ProcessTrx {

    private final ValidationAggregator validator;
    private final Accountant accountant;
    private final AccountService accountService;
    private TrxValidation trxValidation;
    private TransactionRequestDTO transactionDTO;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TransferTrx(TransactionService transactionService,
                       DocumentService documentService,
                       AccountService accountService,
                       DepositService depositService,
                       @Qualifier(value = "transferValidatorAggregator") ValidationAggregator validator,
                       Accountant accountant) {
        this.transactionService = transactionService;
        this.documentService = documentService;
        this.accountService = accountService;
        this.validator = validator;
        this.accountant = accountant;
    }

    @Override
    public void initiate(TransactionRequestDTO transactionRequest) {
        this.transactionDTO = transactionRequest;
        setTrxValidation(transactionRequest);
    }

    @Override
    public void doTransaction() {
        EntityTransaction trx = null;
        try {
            trx = entityManager.getTransaction();
            createTransaction(trx);
        }catch (NoSuchElementException | IllegalStateException | OptimisticLockException exception) {
            assert trx != null;
            trx.rollback();
            createTransaction(trx);
        }
    }

    @Override
    public List<TrxValidatorMessages> validate() {
        return validator.aggregate(this.trxValidation);
    }

    private void createTransaction(EntityTransaction trx) {
        Optional<AccountEntity> sourceAccountEntity= accountService.getByAccountNumber(transactionDTO.getSourceAccount().getAccountNumber());
        Optional<AccountEntity> destinationAccountNumber = accountService.getByAccountNumber(transactionDTO.getDestinationAccount().getAccountNumber());
        sourceAccountEntity.ifPresent(account -> {
            AccountEntity lockedSourceAccount = entityManager.find(AccountEntity.class, account.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            AccountEntity lockedDestinationAccount = entityManager.find(AccountEntity.class, destinationAccountNumber.get().getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            TransactionEntity preparedTransactionEntity = setTransactionEntity(transactionDTO, lockedSourceAccount, lockedDestinationAccount);
            trx.begin();
            TransactionEntity transaction = transactionService.createTransaction(preparedTransactionEntity);
            List<TrxValidatorMessages> trxValidatorMessages = accountant.issueDocument(lockedSourceAccount, lockedDestinationAccount, transaction);
            accountService.save(lockedSourceAccount);
            accountService.save(lockedDestinationAccount);
            if (!trxValidatorMessages.isEmpty()) {
                trx.rollback();
            }
            trx.commit();
        });
    }

    //============================================ Private Method ============================================
    private void setTrxValidation(TransactionRequestDTO transactionRequestDTO) {
        this.trxValidation.setSourceAccountNumber(transactionRequestDTO.getSourceAccount().getAccountNumber());
        this.trxValidation.setDestinationAccountNumber(transactionRequestDTO.getDestinationAccount().getAccountNumber());
        this.trxValidation.setAmount(transactionRequestDTO.getAmount());
        this.trxValidation.setTime(transactionRequestDTO.getTime());
    }

    private TransactionEntity setTransactionEntity(TransactionRequestDTO transactionDTO, AccountEntity sourceAccount, AccountEntity destinationAccount) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setType(TransactionType.TRANSFER);
        transactionEntity.setTerminalID(transactionDTO.getTerminalId());
        transactionEntity.setTrxDate(new Date());
        transactionEntity.setAmount(transactionDTO.getAmount());
        transactionEntity.setSourceAccount(sourceAccount);
        transactionEntity.setDestinationAccount(destinationAccount);
        transactionEntity.setDeposit(getAvailableDeposit(sourceAccount.getDeposit()));
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