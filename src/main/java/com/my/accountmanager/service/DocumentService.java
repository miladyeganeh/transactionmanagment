package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.DocumentEntity;
import com.my.accountmanager.domain.enums.TransactionType;

/**
 * @author M.Yeganeh on 31/05/2020.
 */

public interface DocumentService extends BaseCrudService<DocumentEntity> {
//    DocumentEntity createDocument(DocumentEntity documentEntity);
    String createBillNumber(Integer terminalID, String transactionNumber);
    String createDocumentComment(String transactionID, TransactionType transactionType);
}
