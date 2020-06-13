package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.TransactionEntity;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
public interface TransactionService extends BaseCrudService<TransactionEntity> {
    Optional<TransactionEntity> findByTransactionID(String transactionID);
    TransactionEntity createTransaction(TransactionEntity TransactionEntity);
    Page<TransactionEntity> getPageableTrx(int page, int limit);
}
