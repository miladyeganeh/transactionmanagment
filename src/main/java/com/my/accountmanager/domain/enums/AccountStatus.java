package com.my.accountmanager.domain.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author M.Yeganeh on 01/06/2020.
 */
public enum AccountStatus {
    ACTIVE(1),
    SUSPEND(2);

    private final int value;

    AccountStatus(int value) {
        this.value = value;
    }

    private static final Map<Integer, AccountStatus> lookup = Arrays.stream(AccountStatus.values()).
            collect(Collectors.toMap(AccountStatus::getValue, accountStatus -> accountStatus));

    public static AccountStatus getByValue(int value) {
        return lookup.get(value);
    }

    public int getValue() {
        return value;
    }
}
