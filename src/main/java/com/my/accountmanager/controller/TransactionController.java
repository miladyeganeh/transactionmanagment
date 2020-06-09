package com.my.accountmanager.controller;

import com.my.accountmanager.business.transaction.ProcessTrx;
import com.my.accountmanager.business.transaction.factory.TrxFactory;
import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.exception.AccountServiceException;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.model.dto.TransactionDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.TransactionResponseDTO;
import com.my.accountmanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author M.Yeganeh on 01/06/2020.
 */
@RestController
@RequestMapping(value = "api/v1/transactions")
public class TransactionController {

    private final TrxFactory trxFactory;
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TrxFactory trxFactory, TransactionService transactionService) {
        this.trxFactory = trxFactory;
        this.transactionService = transactionService;
    }

    @GetMapping("/{transactionID}")
    public ResponseEntity<ResponseDTO<TransactionResponseDTO>> getTransaction(@PathVariable String transactionID) {
        Optional<TransactionEntity> transaction = transactionService.getByTransactionID(transactionID);
        ResponseDTO<TransactionResponseDTO> response = new ResponseDTO<>();
        transaction.ifPresent(trx -> {
            response.setData(TransactionResponseDTO.to(trx));
            response.setDate(LocalDate.now());
        });
        if (response.getData() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<TransactionResponseDTO>> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity;
        ResponseDTO<TransactionResponseDTO> response = new ResponseDTO<>();
        ProcessTrx processTrx = trxFactory.getInstance(TransactionType.getByValue(transactionDTO.getTransactionType()));
        processTrx.initiate(transactionDTO);
        List<TrxValidatorMessages> validateMessages = processTrx.validate();
        if (validateMessages.isEmpty()) {
            try{
                transactionEntity = processTrx.doTransaction();
                response.setData(TransactionResponseDTO.to(transactionEntity));
                response.setDate(LocalDate.now());
            } catch (AccountServiceException exception) {
                response.setMessage(exception.getMessage());
                response.setDate(LocalDate.now());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
