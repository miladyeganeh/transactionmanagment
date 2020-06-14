package com.my.accountmanager.controller;

import com.my.accountmanager.business.transaction.TrxProcessor;
import com.my.accountmanager.business.transaction.factory.TrxProcessorFactory;
import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.domain.entity.TransactionRequestEntity;
import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.TransactionResponseDTO;
import com.my.accountmanager.model.dto.response.withrel.TransactionRestDTO;
import com.my.accountmanager.model.enums.ResponseCode;
import com.my.accountmanager.service.TransactionRequestService;
import com.my.accountmanager.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author M.Yeganeh on 01/06/2020.
 */
@RestController
@RequestMapping(value = "api/v1/transactions")
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    private final TrxProcessorFactory trxProcessorFactory;
    private final TransactionRequestService trxReqService;
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(@Qualifier("trxProcessorFactoryImpl") TrxProcessorFactory trxProcessorFactory,
                                 TransactionRequestService trxReqService, TransactionService transactionService) {
        this.trxProcessorFactory = trxProcessorFactory;
        this.trxReqService = trxReqService;
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/{transactionID}")
    public ResponseEntity<TransactionRestDTO> getTransaction(@PathVariable String transactionID) {
        logger.debug(":::::Start getTransaction, transactionID: " + transactionID);
        Optional<TransactionEntity> transactionEntity = transactionService.findByTransactionID(transactionID);
        if (transactionEntity.isPresent()) {
            TransactionRestDTO from = TransactionRestDTO.from(transactionEntity.get());
            return ResponseEntity.ok(from);
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Doing a transaction for your payment or withdraw", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully transaction was done."),
            @ApiResponse(code = 400, message = "Due to bad input data validation was failed, You can retrieve list of errors"),
            @ApiResponse(code = 500, message = "transaction creation process was crashed due to some system failure, try again")
    })
    @PostMapping
    public ResponseEntity<ResponseDTO<TransactionResponseDTO>> createTransaction(@RequestBody TransactionRequestDTO transactionRequest) {
        logger.debug(":::::Start createTransaction, trxDate: " + transactionRequest.getTrxDate());
        TrxProcessor trxProcessor = trxProcessorFactory.getInstance(TransactionType.getByValue(transactionRequest.getTransactionType()));
        try {
            TransactionResponseDTO transactionResponseDTO = trxProcessor.doTransaction(transactionRequest);
            ResponseDTO<TransactionResponseDTO> responseDto = trxReqService.createTransactionResponse(transactionResponseDTO);
            if (responseDto.getData().getSuccessful()) {
                responseDto.add(linkTo(methodOn(TransactionController.class).getTransaction(responseDto.getData().getTrxID())).withRel("Transaction"));
                return ResponseEntity.ok(responseDto);
            }
            return ResponseEntity.badRequest().body(responseDto);
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.<TransactionResponseDTO>builder().withMessage(exception.getMessage())
                            .withCode(ResponseCode.INTERNAL_ERROR).withDate(new Date())
                            .build());
        }
    }

    @ApiOperation(value = "View a specific transaction details", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved details"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("transaction-request/{transactionID}")
    public ResponseEntity<ResponseDTO<TransactionResponseDTO>> getTransactionRequest(@PathVariable String transactionID) {
        logger.debug(":::::Start getTransactionRequest, transactionID: " + transactionID);
        Optional<TransactionRequestEntity> transaction = trxReqService.findByTransactionID(transactionID);
        return transaction
                .map(trx -> ResponseEntity.ok().body(trxReqService.createTransactionResponse(TransactionResponseDTO.from(trx))))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }
}
