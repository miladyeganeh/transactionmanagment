package com.my.accountmanager.domain.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum TransactionType {
    TRANSFER(1),
    WITHDRAW(2),
    DEPOSIT(3),
    REVERSAL(4);

    private final int value;

    TransactionType(int value) {
        this.value = value;
    }

    private static final Map<Integer, TransactionType> lookup = Arrays.stream(TransactionType.values()).
            collect(Collectors.toMap(TransactionType::getValue, transactionType -> transactionType));

    public static TransactionType getByValue(int value) {
        return lookup.get(value);
    }

    public int getValue() {
        return value;
    }
}
