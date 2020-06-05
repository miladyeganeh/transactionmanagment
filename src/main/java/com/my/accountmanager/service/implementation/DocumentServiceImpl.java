package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.DocumentEntity;
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
}
