package com.my.accountmanager.business.transaction;

import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.model.TrxValidatorMessages;
import com.my.accountmanager.model.dto.TransactionDTO;
import org.springframework.stereotype.Component;

import javax.persistence.EntityTransaction;
import java.util.List;

@Component("reversalTrx")
public class ReversalTrx extends ProcessTrx{
    @Override
    public void initiate(TransactionDTO transactionRequestDTO) {

    }

    @Override
    public List<TrxValidatorMessages> validate() {
        return null;
    }

    @Override
    public TransactionEntity doTransaction() {
        return null;
    }

    @Override
    protected TransactionEntity createTransaction(EntityTransaction trx) {
        return null;
    }
}
