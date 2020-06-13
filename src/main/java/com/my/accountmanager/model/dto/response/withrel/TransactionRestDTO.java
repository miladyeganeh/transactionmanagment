package com.my.accountmanager.model.dto.response.withrel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.my.accountmanager.controller.AccountController;
import com.my.accountmanager.controller.CurrencyController;
import com.my.accountmanager.controller.DocumentController;
import com.my.accountmanager.controller.TransactionController;
import com.my.accountmanager.domain.entity.TransactionEntity;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class TransactionRestDTO extends RepresentationModel<TransactionRestDTO> {

    private Long id;
    private String transactionID;
    private Integer terminalID;
    private Double amount;
    private String type;
    private Date trxDate;
    private Long currencyID;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private Long depositID;
    private String reversTransactionID;
    private String mainTransactionID;
    private Long documentID;

    public static TransactionRestDTO from(TransactionEntity transactionEntity) {
        TransactionRestDTO transactionRestDTO = new TransactionRestDTO();
        transactionRestDTO.setId(transactionEntity.getId());
        transactionRestDTO.setTransactionID(transactionEntity.getTransactionID());
        transactionRestDTO.setTerminalID(transactionEntity.getTerminalID());
        transactionRestDTO.setAmount(transactionEntity.getAmount());
        transactionRestDTO.setType(transactionEntity.getType().toString());
        transactionRestDTO.setTrxDate(transactionEntity.getTrxDate());
        transactionRestDTO.setCurrencyID(transactionEntity.getCurrency().getId());
        transactionRestDTO.setSourceAccountNumber(transactionEntity.getSourceAccount().getAccountNumber());
        transactionRestDTO.setDestinationAccountNumber(transactionEntity.getDestinationAccount().getAccountNumber());
        transactionRestDTO.setDepositID(transactionEntity.getDeposit().getId());
        if (transactionEntity.getReversTransaction() != null) {
            transactionRestDTO.setReversTransactionID(transactionEntity.getReversTransaction().getTransactionID());
        }
        if (transactionEntity.getMainTransaction() != null) {
            transactionRestDTO.setMainTransactionID(transactionEntity.getMainTransaction().getTransactionID());
        }
        transactionRestDTO.setDocumentID(transactionEntity.getDocumentEntity().getId());
        return setLinks(transactionRestDTO);
    }

    private static TransactionRestDTO setLinks(TransactionRestDTO transactionRestDTO) {
        transactionRestDTO.add(linkTo(methodOn(TransactionController.class).getTransaction(transactionRestDTO.getTransactionID())).withSelfRel());
        transactionRestDTO.add(linkTo(methodOn(AccountController.class).getAccount(transactionRestDTO.getSourceAccountNumber())).withRel("Source Account"));
        transactionRestDTO.add(linkTo(methodOn(AccountController.class).getAccount(transactionRestDTO.getDestinationAccountNumber())).withRel("Destination Account"));
        transactionRestDTO.add(linkTo(methodOn(CurrencyController.class).getCurrency(transactionRestDTO.getCurrencyID())).withRel("Currency"));
        transactionRestDTO.add(linkTo(methodOn(DocumentController.class).getDocument(transactionRestDTO.getDocumentID())).withRel("Document"));
        return transactionRestDTO;
    }

    public Long getId() {
        return id;
    }

    @JsonCreator
    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionID() {
        return transactionID;
    }

    @JsonCreator
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Integer getTerminalID() {
        return terminalID;
    }

    @JsonCreator
    public void setTerminalID(Integer terminalID) {
        this.terminalID = terminalID;
    }

    public Double getAmount() {
        return amount;
    }

    @JsonCreator
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    @JsonCreator
    public void setType(String type) {
        this.type = type;
    }

    public Date getTrxDate() {
        return trxDate;
    }

    @JsonCreator
    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }

    public Long getCurrencyID() {
        return currencyID;
    }

    @JsonCreator
    public void setCurrencyID(Long currencyID) {
        this.currencyID = currencyID;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    @JsonCreator
    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    @JsonCreator
    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public Long getDepositID() {
        return depositID;
    }

    @JsonCreator
    public void setDepositID(Long depositID) {
        this.depositID = depositID;
    }

    public String getReversTransactionID() {
        return reversTransactionID;
    }

    @JsonCreator
    public void setReversTransactionID(String reversTransactionID) {
        this.reversTransactionID = reversTransactionID;
    }

    public String getMainTransactionID() {
        return mainTransactionID;
    }

    @JsonCreator
    public void setMainTransactionID(String mainTransactionID) {
        this.mainTransactionID = mainTransactionID;
    }

    public Long getDocumentID() {
        return documentID;
    }

    @JsonCreator
    public void setDocumentID(Long documentID) {
        this.documentID = documentID;
    }
}
