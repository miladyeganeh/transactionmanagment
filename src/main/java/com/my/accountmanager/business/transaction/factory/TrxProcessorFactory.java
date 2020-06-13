package com.my.accountmanager.business.transaction.factory;

import com.my.accountmanager.business.transaction.TrxProcessor;
import com.my.accountmanager.domain.enums.TransactionType;

public interface TrxProcessorFactory {
    TrxProcessor getInstance(TransactionType transactionType);
}
