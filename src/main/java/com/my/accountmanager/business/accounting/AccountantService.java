package com.my.accountmanager.business.accounting;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.entity.DocumentEntity;
import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;

public interface AccountantService {
    DocumentEntity issueDocument(AccountEntity sourceAccount, AccountEntity destinationAccount, TrxInfo trxInfo, TransactionRequestDTO trxReq);
}
