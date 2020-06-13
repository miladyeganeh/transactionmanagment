package com.my.accountmanager.business.transaction;

import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import com.my.accountmanager.model.dto.response.TransactionResponseDTO;
import com.my.accountmanager.service.DocumentService;
import com.my.accountmanager.service.TransactionService;

import java.util.List;

/**
 * @author M.Yeganeh on 01/06/2020.
 */
public abstract class TrxProcessor {

     protected TransactionService transactionService;
     protected DocumentService documentService;

     public TrxInfo initiate(TransactionRequestDTO transactionRequestDTO) {
          return TrxInfo.from(transactionRequestDTO);
     }

     public abstract List<String> validate(TrxInfo trxInfo, TransactionRequestDTO trxReq);
     public abstract TransactionResponseDTO doTransaction(TransactionRequestDTO trxReq);
     protected abstract TransactionResponseDTO processTransaction(TransactionRequestDTO trxReq);
     public abstract TransactionType getTrxType();
}
