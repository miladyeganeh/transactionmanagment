package com.my.accountmanager.model.dto.response;

import com.my.accountmanager.domain.entity.TransactionEntity;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class TransactionResponseDTO {
    private String trxID;
    private Date time;
    private Double amount;
    private Integer terminalId;
    private Integer transactionType;

    public static TransactionResponseDTO to(TransactionEntity transactionEntity) {
        return new ModelMapper().map(transactionEntity, TransactionResponseDTO.class);
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
}
