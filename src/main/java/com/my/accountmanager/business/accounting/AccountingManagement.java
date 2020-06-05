package com.my.accountmanager.business.accounting;

import com.my.accountmanager.service.DocumentItemService;
import com.my.accountmanager.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountingManagement implements Accountant {

    private DocumentService documentService;
    private DocumentItemService documentItemService;

    @Autowired
    public AccountingManagement(DocumentService documentService, DocumentItemService documentItemService) {
        this.documentService = documentService;
        this.documentItemService = documentItemService;
    }

    @Override
    public void issueDocument() {

    }

    @Override
    public void reversDocument() {

    }
}
