package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.DocumentEntity;
import com.my.accountmanager.domain.enums.TransactionType;

public interface DocumentService extends BaseCrudService<DocumentEntity> {
    String createBillNumber(Integer terminalID, String transactionNumber);
    String createDocumentComment(String transactionID, TransactionType transactionType);
}
