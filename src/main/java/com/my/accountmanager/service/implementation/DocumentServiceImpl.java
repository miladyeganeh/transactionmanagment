package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.DocumentEntity;
import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.repository.DocumentRepository;
import com.my.accountmanager.service.DocumentService;
import com.my.accountmanager.utils.DateUtil;
import com.my.accountmanager.utils.GlobalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author M.Yeganeh on 31/05/2020.
 */

@Service
public class DocumentServiceImpl extends BaseCrudServiceImpl<DocumentEntity, DocumentRepository> implements DocumentService {
    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Autowired
    public DocumentServiceImpl(DocumentRepository repository) {
        super(repository);
    }

//    @Override
//    public DocumentEntity createDocument(DocumentEntity documentEntity) {
//        logger.info(":::::Start createDocument, transactionId: " + documentEntity.getTransaction().getTransactionID());
//        DocumentEntity saveDocument = super.save(documentEntity);
//        logger.info(":::::Start createDocument.");
//        return saveDocument;
//    }

    @Override
    public String createDocumentComment(String transactionID, TransactionType transactionType) {
        logger.info(":::::Start createDocumentComment, transactionId: " + transactionID);
        String comment = "Document is issued for transfer transaction with transactionID: " +
                transactionID;
        logger.info(":::::Start createDocumentComment");
        return comment;
    }

    @Override
    public String createBillNumber(Integer terminalID, String transactionID) {
        logger.info(":::::Start createBillNumber, terminalID: " + terminalID + ", transactionID: " + transactionID);
        return  DateUtil.getYearMonthDay() +
                GlobalConstant.ASTRIX +
                terminalID +
                GlobalConstant.ASTRIX +
                transactionID;
    }
}
