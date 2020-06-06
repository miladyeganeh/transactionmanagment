package com.my.accountmanager.business.transaction;

import com.my.accountmanager.domain.dto.request.TransactionDTO;
import com.my.accountmanager.model.TrxValidatorMessages;

import java.util.List;


public class ReversalTrx extends ProcessTrx{
    @Override
    void initiate(TransactionDTO transactionRequestDTO) {

    }

    @Override
    protected List<TrxValidatorMessages> validate() {
        return null;
    }

    @Override
    void doTransaction() {

    }
}
