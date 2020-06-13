package com.my.accountmanager.controller;

import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.withrel.TransactionRestDTO;
import com.my.accountmanager.model.enums.ResponseCode;
import com.my.accountmanager.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@RestController
@RequestMapping(value = "api/v1/reports")
public class ReportController {

    private final TransactionService transactionService;

    public ReportController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "retrieve a list of transaction in specific period", response = ResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found data and return it"),
            @ApiResponse(code = 404, message = "No transaction in your period"),
    })
    @GetMapping(value = "/transactions")
    public ResponseEntity<ResponseDTO<List<TransactionRestDTO>>> getTransactionsReport (@RequestParam(value = "page" ,defaultValue = "0") int page,
                                                                                       @RequestParam(value = "limit" ,defaultValue = "10") int limit){
        List<TransactionRestDTO> transactionRestDTOS = new ArrayList<>();
        Page<TransactionEntity> pageableTransactions = transactionService.getPageableTrx(page, limit);
        if (pageableTransactions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        pageableTransactions.forEach(trx -> transactionRestDTOS.add(TransactionRestDTO.from(trx)));
        return ResponseEntity.ok().body(ResponseDTO.<List<TransactionRestDTO>>builder().withMessage("")
                        .withData(transactionRestDTOS)
                        .withCode(ResponseCode.FOUND_CONTENT).withDate(new Date())
                        .build());
    }
}
