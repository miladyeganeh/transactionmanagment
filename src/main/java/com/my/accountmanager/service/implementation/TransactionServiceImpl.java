package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.repository.TransactionRepository;
import com.my.accountmanager.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl extends BaseCrudServiceImpl<TransactionEntity, TransactionRepository> implements TransactionService {
    private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        super(transactionRepository);
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Optional<TransactionEntity> findByTransactionID(String transactionID) {
        logger.debug(":::::Start getByTransactionID, transactionID: " + transactionID);
        Optional<TransactionEntity> transactionEntity = this.transactionRepository.findByTransactionID(transactionID);
        logger.debug(":::::Finish getByTransactionID");
        return transactionEntity;
    }

    @Override
    public TransactionEntity createTransaction(TransactionEntity transactionEntity) {
        logger.debug(":::::Start createTransaction, transactionID: " + transactionEntity.getTransactionID());
        TransactionEntity saveTransaction = super.save(transactionEntity);
        logger.debug(":::::Finish createTransaction");
        return saveTransaction;
    }

    @Override
    public Page<TransactionEntity> getPageableTrx(int page, int limit) {
        if (page > 0) {
            page -=1;
        }
        Pageable pageableRequest = PageRequest.of(page, limit);
        return repository.findAll(pageableRequest);
    }

    public List<TransactionEntity> getByTrxDate() {
        return null;
    }
}
