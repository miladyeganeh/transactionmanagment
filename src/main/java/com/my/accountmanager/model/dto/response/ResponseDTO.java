package com.my.accountmanager.model.dto.response;

import com.my.accountmanager.model.enums.ResponseCode;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

public class ResponseDTO<T> extends RepresentationModel<ResponseDTO<T>> implements Serializable {
    private T data;
    private Date date;
    private String message;
    private ResponseCode code;

    public ResponseDTO() {
    }

    public ResponseDTO(T data, Date date, String message, ResponseCode code) {
        this.data = data;
        this.date = date;
        this.message = message;
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public ResponseCode getCode() {
        return code;
    }

    public static <T> ResponseDTOBuilder<T> builder(){
        return new ResponseDTOBuilder<>();
    }

    public static class ResponseDTOBuilder<T> {
        private T data;
        private Date date;
        private String message;
        private ResponseCode code;

        public ResponseDTOBuilder<T> withMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseDTOBuilder<T> withCode(ResponseCode code) {
            this.code = code;
            return this;
        }

        public ResponseDTOBuilder<T> withDate(Date date) {
            this.date = date;
            return this;
        }

        public ResponseDTOBuilder<T> withData(T data) {
            this.data = data;
            return this;
        }

        public ResponseDTO<T> build(){
            return new ResponseDTO<T>(data, date, message, code);
        }
    }
}
