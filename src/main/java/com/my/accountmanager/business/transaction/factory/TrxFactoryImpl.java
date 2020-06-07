package com.my.accountmanager.business.transaction.factory;

import com.my.accountmanager.business.transaction.ProcessTrx;
import com.my.accountmanager.domain.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TrxFactoryImpl implements TrxFactory {

    private ProcessTrx transferTrx;
    private ProcessTrx depositTrx;
    private ProcessTrx reversalTrx;
    private ProcessTrx withdrawTrx;

    @Autowired
    public TrxFactoryImpl(@Qualifier("transferTrx") ProcessTrx transferTrx, @Qualifier("depositTrx") ProcessTrx depositTrx,
                          @Qualifier("reversalTrx") ProcessTrx reversalTrx, @Qualifier("withdrawTrx") ProcessTrx withdrawTrx) {
        this.transferTrx = transferTrx;
        this.depositTrx = depositTrx;
        this.reversalTrx = reversalTrx;
        this.withdrawTrx = withdrawTrx;
    }

    @Override
    public ProcessTrx getInstance(TransactionType transactionType) {
        switch (transactionType) {
            case TRANSFER:
                return this.transferTrx;
            case DEPOSIT:
                return this.depositTrx;
            case WITHDRAW:
                return this.withdrawTrx;
            case REVERSAL:
                return this.reversalTrx;
            default:
                return null;
        }
    }
}
