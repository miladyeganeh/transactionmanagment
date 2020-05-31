package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.DocumentItemEntity;
import com.my.accountmanager.repository.DocumentItemRepository;
import com.my.accountmanager.service.DocumentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author M.Yeganeh on 31/05/2020.
 */

@Service
public class DocumentItemServiceImpl extends BaseServiceImpl<DocumentItemEntity, DocumentItemRepository> implements DocumentItemService {
    @Autowired
    public DocumentItemServiceImpl(DocumentItemRepository repository) {
        super(repository);
    }
}
