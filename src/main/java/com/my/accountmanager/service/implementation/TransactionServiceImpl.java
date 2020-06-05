package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.repository.TransactionRepository;
import com.my.accountmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl extends BaseCrudServiceImpl<TransactionEntity, TransactionRepository> implements TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        super(transactionRepository);
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Optional<TransactionEntity> getByTransactionID(String transactionID) {
        return Optional.of(this.transactionRepository.findByTransactionID(transactionID));
    }

    @Override
    public boolean isUniqueTransactionId(String transactionID) {
        Optional<TransactionEntity> duplicatedTransaction = getByTransactionID(transactionID);
        return duplicatedTransaction.isEmpty();
    }
}
