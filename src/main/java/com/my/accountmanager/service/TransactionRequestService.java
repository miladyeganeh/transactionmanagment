package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.domain.entity.TransactionRequestEntity;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.TransactionResponseDTO;

import java.util.List;
import java.util.Optional;

public interface TransactionRequestService extends BaseCrudService<TransactionRequestEntity> {
    TransactionRequestEntity saveFailedTransactionRequest(TransactionRequestDTO trxReq, List<String> validationMessages);
    TransactionRequestEntity saveSuccessTransactionRequest(TransactionRequestDTO trxReq, TransactionEntity transactionEntity);
    Optional<TransactionRequestEntity> findByTransactionID(String transactionId);
    ResponseDTO<TransactionResponseDTO> createTransactionResponse(TransactionResponseDTO trxRes);
}
