package com.my.accountmanager.business.transaction;

import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import com.my.accountmanager.model.dto.response.TransactionResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("reversalTrx")
public class ReversalTrx extends TrxProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ReversalTrx.class);

    @Override
    public List<String> validate(TrxInfo trxInfo, TransactionRequestDTO trxReq) {
        return null;
    }

    @Override
    public TransactionResponseDTO doTransaction(TransactionRequestDTO trxReq) {
        return null;
    }

    @Override
    protected TransactionResponseDTO processTransaction(TransactionRequestDTO trxReq) {
        return null;
    }

    @Override
    public TransactionType getTrxType() {
        return TransactionType.REVERSAL;
    }
}
