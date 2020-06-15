package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.DocumentItemEntity;
import com.my.accountmanager.repository.DocumentItemRepository;
import com.my.accountmanager.service.DocumentItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DocumentItemServiceImpl extends BaseCrudServiceImpl<DocumentItemEntity, DocumentItemRepository> implements DocumentItemService {
    private static final Logger logger = LoggerFactory.getLogger(DocumentItemServiceImpl.class);

    @Autowired
    public DocumentItemServiceImpl(DocumentItemRepository repository) {
        super(repository);
    }
}
