package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.TransactionEntity;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
public interface TransactionService {
    Optional<TransactionEntity> getByTransactionID(String transactionID);
    boolean isUniqueTransactionId(String transactionID);
}
