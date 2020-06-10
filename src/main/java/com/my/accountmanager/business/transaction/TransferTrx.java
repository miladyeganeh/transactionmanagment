package com.my.accountmanager.business.transaction;

import com.my.accountmanager.business.accounting.AccountantService;
import com.my.accountmanager.business.transaction.validation.aggregator.ValidationAggregator;
import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.entity.CurrencyEntity;
import com.my.accountmanager.domain.entity.DepositEntity;
import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.exception.AccountServiceException;
import com.my.accountmanager.exception.CurrencyServiceException;
import com.my.accountmanager.exception.configuration.TransactionServiceException;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.model.dto.TransactionDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.TransactionResponseDTO;
import com.my.accountmanager.service.AccountService;
import com.my.accountmanager.service.CurrencyService;
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
    private final AccountantService accountantService;
    private final AccountService accountService;
    private final CurrencyService currencyService;
    private TransactionDTO transactionDTO;
    TrxValidation trxValidation;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TransferTrx(TransactionService transactionService,
                       DocumentService documentService,
                       AccountService accountService,
                       @Qualifier(value = "transferValidatorAggregator") ValidationAggregator validator,
                       AccountantService accountantService,
                       CurrencyService currencyService) {
        this.transactionService = transactionService;
        this.documentService = documentService;
        this.accountService = accountService;
        this.currencyService = currencyService;
        this.validator = validator;
        this.accountantService = accountantService;
    }

    @Override
    public void initiate(TransactionDTO transactionDTO) {
        this.transactionDTO = transactionDTO;
        this.setTrxValidation(transactionDTO);
    }

    @Override
    public List<TrxValidatorMessages> validate() {
        return validator.aggregate(this.trxValidation);
    }

    @Override
    public ResponseDTO<TransactionResponseDTO> doTransaction() {
        boolean needSecondTry = true;
        ResponseDTO<TransactionResponseDTO> responseDTO = new ResponseDTO<>();
        TransactionEntity transaction;
        EntityTransaction trx = null;
        try {
            trx = entityManager.getTransaction();
            transaction = createTransaction(trx);
            needSecondTry = false;
            responseDTO.setData(TransactionResponseDTO.to(transaction));
            return responseDTO;
        } catch (TransactionServiceException | AccountServiceException | CurrencyServiceException ex) {
            if (trx != null) {
                trx.rollback();
            }
            if (needSecondTry) {
                needSecondTry = false;
                trx = entityManager.getTransaction();
                transaction = createTransaction(trx);
                responseDTO.setData(TransactionResponseDTO.to(transaction));
                return responseDTO;
            }
            responseDTO.getData().setErrorList(generateErrorList(ex.getMessage()));
            return responseDTO;
        } catch (NoSuchElementException | IllegalStateException | OptimisticLockException ex2) {
            throw new TransactionServiceException("transaction cannot be processed");
        }
    }

    @Override
    public TransactionEntity createTransaction(EntityTransaction trx) {
        TransactionEntity transaction;
        Optional<AccountEntity> sourceAccountEntity = accountService.getByAccountNumber(transactionDTO.getSourceAccount().getAccountNumber());
        Optional<AccountEntity> destinationAccountNumber = accountService.getByAccountNumber(transactionDTO.getDestinationAccount().getAccountNumber());
        if (sourceAccountEntity.isEmpty()) {
            throw new AccountServiceException("Source Account is invalid");
        } else {
            if (destinationAccountNumber.isEmpty()) {
                throw new AccountServiceException("Destination Account is invalid");
            }
            AccountEntity lockedSourceAccount = entityManager.find(AccountEntity.class, sourceAccountEntity.get().getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            AccountEntity lockedDestinationAccount = entityManager.find(AccountEntity.class, destinationAccountNumber.get().getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            TransactionEntity preparedTransactionEntity = setTransactionEntity(transactionDTO, lockedSourceAccount, lockedDestinationAccount);
            trx.begin();
            transaction = transactionService.createTransaction(preparedTransactionEntity);
            accountantService.initiate(this.trxValidation);
            List<TrxValidatorMessages> trxValidatorMessages = accountantService.issueDocument(lockedSourceAccount, lockedDestinationAccount, transaction);
            if (!trxValidatorMessages.isEmpty()) {
                trx.rollback();
                throw new TransactionServiceException("Has not enough balance");
            }
            trx.commit();
        }
        return transaction;
    }

    //============================================ Private Method ============================================
    private void setTrxValidation(TransactionDTO transactionDTO) {
        this.trxValidation.setSourceAccountNumber(transactionDTO.getSourceAccount().getAccountNumber());
        this.trxValidation.setDestinationAccountNumber(transactionDTO.getDestinationAccount().getAccountNumber());
        this.trxValidation.setAmount(transactionDTO.getAmount());
        this.trxValidation.setTime(transactionDTO.getTime());
        this.trxValidation.setHashedPassword(transactionDTO.getHashedPassword());
    }

    private TransactionEntity setTransactionEntity(TransactionDTO transactionDTO, AccountEntity sourceAccount, AccountEntity destinationAccount) throws RuntimeException {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTerminalID(transactionDTO.getTerminalId());
        Optional<CurrencyEntity> currencyEntity = currencyService.getCurrencyByCode(transactionDTO.getCurrencyCode());
        currencyEntity.ifPresentOrElse(transactionEntity::setCurrency, () -> {throw new CurrencyServiceException("Can not find your currency");});
        transactionEntity.setType(TransactionType.TRANSFER);
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

    private List<TrxValidatorMessages> generateErrorList(String message) {
        return Collections.singletonList(new TrxValidatorMessages(message, true));
    }

}