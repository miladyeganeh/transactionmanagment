package com.my.accountmanager.business.transaction.validation.aggregator;

import com.my.accountmanager.model.TrxInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;

import java.util.List;
import java.util.function.BiConsumer;

public interface ValidationAggregator {
    BiConsumer<TrxInfo, TransactionRequestDTO> getValidationChain();

    default List<String> aggregate(TrxInfo trxInfo, TransactionRequestDTO trxReq) {
        getValidationChain().accept(trxInfo, trxReq);
        return trxInfo.getValidationErrors();
    }
}
