package com.my.accountmanager.controller;

import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.withrel.TransactionRestDTO;
import com.my.accountmanager.model.enums.ResponseCode;
import com.my.accountmanager.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/v1/reports")
public class ReportController {

    private final TransactionService transactionService;

    public ReportController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/transactions")
    public ResponseEntity<ResponseDTO<List<TransactionRestDTO>>> getTransactionsReport (@RequestParam(value = "page" ,defaultValue = "0") int page,
                                                                                       @RequestParam(value = "limit" ,defaultValue = "10") int limit){
        List<TransactionRestDTO> transactionRestDTOS = new ArrayList<>();
        Page<TransactionEntity> pageableTransactions = transactionService.getPageableTrx(page, limit);
        pageableTransactions.forEach(trx -> transactionRestDTOS.add(TransactionRestDTO.from(trx)));
        transactionRestDTOS.forEach(trxDto -> {
            trxDto.add(linkTo(methodOn(TransactionController.class).getTransaction(trxDto.getTransactionID())).withSelfRel());
            trxDto.add(linkTo(methodOn(AccountController.class).getAccount(trxDto.getSourceAccountNumber())).withRel("Source Account"));
            trxDto.add(linkTo(methodOn(AccountController.class).getAccount(trxDto.getDestinationAccountNumber())).withRel("Destination Account"));
            trxDto.add(linkTo(methodOn(CurrencyController.class).getCurrency(trxDto.getCurrencyID())).withRel("Currency"));
            trxDto.add(linkTo(methodOn(DocumentController.class).getDocument(trxDto.getDocumentID())).withRel("Document"));
        });
        if (transactionRestDTOS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().body(ResponseDTO.<List<TransactionRestDTO>>builder().withMessage("")
                        .withData(transactionRestDTOS)
                        .withCode(ResponseCode.FOUND_CONTENT).withDate(new Date())
                        .build());

    }
}
