package com.my.accountmanager.business.transaction;

import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.model.dto.TransactionDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.TransactionResponseDTO;
import com.my.accountmanager.service.DocumentService;
import com.my.accountmanager.service.TransactionService;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * @author M.Yeganeh on 01/06/2020.
 */
public abstract class ProcessTrx {

     TransactionService transactionService;
     DocumentService documentService;

     public abstract void initiate(TransactionDTO transactionRequestDTO);
     public abstract List<TrxValidatorMessages> validate();
     public abstract ResponseDTO<TransactionResponseDTO> doTransaction();
     protected abstract TransactionEntity createTransaction(EntityTransaction trx);
}
