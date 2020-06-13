package com.my.accountmanager.business.accounting;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.entity.DocumentEntity;
import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;

public interface AccountantService {
    DocumentEntity issueDocument(AccountEntity sourceAccount, AccountEntity destinationAccount, TrxInfo trxInfo, TransactionRequestDTO trxReq);
//    void initiate(TrxInfo trxValidation);
//    List<TrxValidatorMessages> issueDocument(AccountEntity sourceAccount, AccountEntity destinationAccount, TransactionEntity transactionEntity);
//    void calculate(AccountEntity sourceAccount, AccountEntity destinationAccount, TransactionEntity transactionEntity);
//    void reversDocument();
//    List<TrxValidatorMessages> validate();
}
