package com.my.accountmanager.model.dto.response;

import com.my.accountmanager.domain.entity.TransactionRequestEntity;
import com.my.accountmanager.model.enums.ResponseCode;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

public class TransactionResponseDTO extends RepresentationModel<TransactionResponseDTO> implements Serializable {
    private String trxID;
    private Date time;
    private Double amount;
    private Integer terminalId;
    private Integer transactionType;
    private String message;
    private Boolean successful;

    public static TransactionResponseDTO from(TransactionRequestEntity requestEntity) {
        TransactionResponseDTO responseDTO = new TransactionResponseDTO();
        responseDTO.setAmount(requestEntity.getAmount());
        responseDTO.setSuccessful(requestEntity.getResponseCode().equals(ResponseCode.SUCCESS));
        responseDTO.setTerminalId(requestEntity.getTerminalId());
        responseDTO.setTime(new Date());
        responseDTO.setTransactionType(requestEntity.getTransactionType());
        responseDTO.setTrxID(requestEntity.getTrxID());
        responseDTO.setMessage(requestEntity.getMessage());
        return responseDTO;
    }

    public String getTrxID() {
        return trxID;
    }

    public void setTrxID(String trxID) {
        this.trxID = trxID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }
}
