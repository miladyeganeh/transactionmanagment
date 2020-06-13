package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.domain.entity.TransactionRequestEntity;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.TransactionResponseDTO;
import com.my.accountmanager.model.enums.ResponseCode;
import com.my.accountmanager.repository.TransactionRequestRepository;
import com.my.accountmanager.service.TransactionRequestService;
import com.my.accountmanager.utils.GlobalConstant;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Service
public class TransactionRequestServiceImpl extends BaseCrudServiceImpl<TransactionRequestEntity, TransactionRequestRepository> implements TransactionRequestService {

    public TransactionRequestServiceImpl(TransactionRequestRepository repository) {
        super(repository);
    }

    @Override
    public TransactionRequestEntity saveFailedTransactionRequest(TransactionRequestDTO trxReq, List<String> validationMessages){
        TransactionRequestEntity requestEntity = TransactionRequestDTO.to(trxReq);
        requestEntity.setMessage(String.join(GlobalConstant.MESSAGE_SEPARATOR, validationMessages));
        requestEntity.setResponseCode(ResponseCode.FAIL);
        requestEntity = save(requestEntity);
        return requestEntity;
    }

    @Override
    public TransactionRequestEntity saveSuccessTransactionRequest(TransactionRequestDTO trxReq, TransactionEntity transactionEntity) {
        trxReq.setTrxID(transactionEntity.getTransactionID());
        TransactionRequestEntity requestEntity = TransactionRequestDTO.to(trxReq);
        requestEntity.setTransaction(transactionEntity);
        requestEntity.setResponseCode(ResponseCode.SUCCESS);
        requestEntity = save(requestEntity);
        return requestEntity;
    }

    @Override
    public Optional<TransactionRequestEntity> findByTransactionID(String transactionId) {
        return this.repository.findByTrxID(transactionId);
    }

    @Override
    public ResponseDTO<TransactionResponseDTO> createTransactionResponse(TransactionResponseDTO trxRes) {
        return ResponseDTO.<TransactionResponseDTO>builder()
                .withData(trxRes)
                .withDate(new Date())
                .build();
    }
}
