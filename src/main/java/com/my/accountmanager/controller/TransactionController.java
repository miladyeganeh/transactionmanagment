package com.my.accountmanager.controller;

import com.my.accountmanager.business.transaction.ProcessTrx;
import com.my.accountmanager.business.transaction.factory.TrxFactory;
import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.exception.configuration.TransactionServiceException;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.model.dto.TransactionDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.TransactionResponseDTO;
import com.my.accountmanager.model.enums.ResponseCode;
import com.my.accountmanager.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @ApiOperation(value = "View a specific transaction details", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved details"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/{transactionID}")
    public ResponseEntity<ResponseDTO<TransactionResponseDTO>> getTransaction(@PathVariable String transactionID) {
        Optional<TransactionEntity> transaction = transactionService.getByTransactionID(transactionID);
        ResponseDTO<TransactionResponseDTO> response = new ResponseDTO<>();
        transaction.ifPresent(trx -> {
            response.setData(TransactionResponseDTO.to(trx));
            response.setDate(new Date());
        });
        if (response.getData() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Doing a transaction for your payment or withdraw", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully transaction was done."),
            @ApiResponse(code = 400, message = "Due to bad input data validation was failed, You can retrieve list of errors"),
            @ApiResponse(code = 500, message = "transaction creation process was crashed due to some system failure, try again")
    })
    @PostMapping
    public ResponseEntity<ResponseDTO<TransactionResponseDTO>> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        ResponseDTO<TransactionResponseDTO> response = new ResponseDTO<>();
        ProcessTrx processTrx = trxFactory.getInstance(TransactionType.getByValue(transactionDTO.getTransactionType()));
        processTrx.initiate(transactionDTO);
        List<TrxValidatorMessages> errorMessages = processTrx.validate();
        if (!errorMessages.isEmpty()) {
            response.setDate(new Date());
            response.setCode(ResponseCode.VALIDATION_FAILED);
            response.getData().setErrorList(errorMessages);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try{
            response = processTrx.doTransaction();
            response.setDate(new Date());
            response.setCode(ResponseCode.SUCCESS);
        } catch (TransactionServiceException exception) {
            response.setDate(new Date());
            response.setCode(ResponseCode.INTERNAL_ERROR);
            response.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
