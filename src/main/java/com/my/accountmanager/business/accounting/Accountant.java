package com.my.accountmanager.business.accounting;

import com.my.accountmanager.domain.dto.request.TransactionDTO;
import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.model.TrxValidatorMessages;

import java.util.List;

public interface Accountant {
    List<TrxValidatorMessages> issueDocument(AccountEntity account, TransactionDTO transactionDTO);
    void reversDocument();
    List<TrxValidatorMessages> validate();
}
