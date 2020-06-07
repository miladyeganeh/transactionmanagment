package com.my.accountmanager.controller;

import com.my.accountmanager.business.transaction.ProcessTrx;
import com.my.accountmanager.business.transaction.factory.TrxFactory;
import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author M.Yeganeh on 01/06/2020.
 */
@RestController
@RequestMapping("api/transaction")
public class TransactionController {

    private TrxFactory trxFactory;

    @Autowired
    public TransactionController(TrxFactory trxFactory) {
        this.trxFactory = trxFactory;
    }

    @PostMapping
    public void createTransaction(@RequestBody TransactionRequestDTO transactionDTO) {
        ProcessTrx processTrx = trxFactory.getInstance(TransactionType.getByValue(transactionDTO.getTransactionType()));
        processTrx.initiate(transactionDTO);
        List<TrxValidatorMessages> validateMessages = processTrx.validate();
        if (validateMessages.isEmpty()) {
            processTrx.doTransaction();
        }
    }
}
