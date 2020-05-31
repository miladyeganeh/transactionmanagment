package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.TransactionEntity;
import com.my.accountmanager.repository.TransactionRepository;
import com.my.accountmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl extends BaseServiceImpl<TransactionEntity, TransactionRepository> implements TransactionService {
    @Autowired
    public TransactionServiceImpl(TransactionRepository repository) {
        super(repository);
    }
}
