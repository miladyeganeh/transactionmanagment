package com.my.accountmanager.model.dto.response;

import com.my.accountmanager.model.enums.ResponseCode;

import java.time.LocalDate;

public class ResponseDTO<T> {
    private String message;
    private ResponseCode code;
    private LocalDate date;
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
