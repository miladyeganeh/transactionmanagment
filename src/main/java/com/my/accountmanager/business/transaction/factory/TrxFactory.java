package com.my.accountmanager.business.transaction.factory;

import com.my.accountmanager.business.transaction.ProcessTrx;
import com.my.accountmanager.domain.enums.TransactionType;

public interface TrxFactory {
    ProcessTrx getInstance(TransactionType transactionType);
}
