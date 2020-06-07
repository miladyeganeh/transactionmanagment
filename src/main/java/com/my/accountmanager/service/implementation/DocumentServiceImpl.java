package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.DocumentEntity;
import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.repository.DocumentRepository;
import com.my.accountmanager.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author M.Yeganeh on 31/05/2020.
 */

@Service
public class DocumentServiceImpl extends BaseCrudServiceImpl<DocumentEntity, DocumentRepository> implements DocumentService {
    @Autowired
    public DocumentServiceImpl(DocumentRepository repository) {
        super(repository);
    }

    @Override
    public DocumentEntity createDocument(DocumentEntity documentEntity) {
        return super.save(documentEntity);
    }

    @Override
    public String createDocumentComment(String transactionID, TransactionType transactionType) {
        StringBuilder comment = new StringBuilder();
        comment.append("Document is issued for transfer transaction with transactionID: ");
        comment.append(transactionID);
        return comment.toString();
    }
}
