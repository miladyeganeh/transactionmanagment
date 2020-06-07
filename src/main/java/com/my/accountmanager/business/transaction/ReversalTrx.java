package com.my.accountmanager.business.transaction;

import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("reversalTrx")
public class ReversalTrx extends ProcessTrx{
    @Override
    public void initiate(TransactionRequestDTO transactionRequestDTO) {

    }

    @Override
    public List<TrxValidatorMessages> validate() {
        return null;
    }

    @Override
    public void doTransaction() {

    }
}
