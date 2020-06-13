package com.my.accountmanager.business.transaction.factory;

import com.my.accountmanager.business.transaction.TrxProcessor;
import com.my.accountmanager.domain.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrxProcessorFactoryImpl implements TrxProcessorFactory{

    private Map<TransactionType, TrxProcessor> processors;

    @Autowired
    public TrxProcessorFactoryImpl(Set<TrxProcessor> processorSet) {
        createProcessor(processorSet);
    }

    private void createProcessor(Set<TrxProcessor> processorSet) {
        processors = processorSet.stream().collect(Collectors.toMap(TrxProcessor::getTrxType, processor -> processor));
    }

    @Override
    public TrxProcessor getInstance(TransactionType transactionType) {
        return processors.get(transactionType);
    }
}
