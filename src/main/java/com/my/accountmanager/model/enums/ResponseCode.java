package com.my.accountmanager.model.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum ResponseCode {
    SUCCESS(1),
    FAIL(2),
    NOT_FOUND_CONTENT(3),
    FOUND_CONTENT(4),
    VALIDATION_FAILED(5),
    INTERNAL_ERROR(6),
    DELETED(7);

    private final int value;

    ResponseCode(int value) {
        this.value = value;
    }

    private static final Map<Integer, ResponseCode> lookup = Arrays.stream(ResponseCode.values()).
            collect(Collectors.toMap(ResponseCode::getValue, responseCode -> responseCode));

    public static ResponseCode getByValue(int value) {
        return lookup.get(value);
    }

    public int getValue() {
        return value;
    }
}
