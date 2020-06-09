package com.my.accountmanager.exception;

import java.io.Serializable;
import java.time.LocalDate;

public class ErrorMessage implements Serializable {
    private String message;
    private LocalDate date;

    public ErrorMessage(String message, LocalDate date) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
