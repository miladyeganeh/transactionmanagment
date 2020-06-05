package com.my.accountmanager.business.transaction;

import com.my.accountmanager.domain.dto.request.TransactionRequestDTO;
import com.my.accountmanager.model.TrxValidation;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.service.DocumentService;
import com.my.accountmanager.service.TransactionService;

import java.util.List;

/**
 * @author M.Yeganeh on 01/06/2020.
 */
public abstract class ProcessTrx {

     TransactionService transactionService;
     DocumentService documentService;

     abstract void initiate(TransactionRequestDTO transactionRequestDTO);
     protected abstract List<TrxValidatorMessages> validate();
     abstract void doTransaction();
}
