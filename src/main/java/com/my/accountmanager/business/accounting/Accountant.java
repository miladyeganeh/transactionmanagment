package com.my.accountmanager.business.accounting;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.model.TrxValidatorMessages;

import java.util.List;

public interface Accountant {
    List<TrxValidatorMessages> issueDocument(AccountEntity sourceAccount, AccountEntity destinationAccount, TransactionEntity transactionEntity);
    void calculate(AccountEntity sourceAccount, AccountEntity destinationAccount, TransactionEntity transactionEntity);
    void reversDocument();
    List<TrxValidatorMessages> validate();
}
