package com.my.accountmanager.model.dto.response.withrel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.my.accountmanager.domain.entity.DocumentEntity;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

public class DocumentRestDTO extends RepresentationModel<DocumentRestDTO> {

    private Long id;
    private String comment;
    private Date issuanceDate;
    private String billNumber;
    private String status;
    private Double totalAmount;
    private Long currencyID;

    public static DocumentRestDTO from(DocumentEntity documentEntity) {
        DocumentRestDTO documentRestDTO = new DocumentRestDTO();
        documentRestDTO.setId(documentEntity.getId());
        documentRestDTO.setComment(documentEntity.getComment());
        documentRestDTO.setIssuanceDate(documentEntity.getIssuanceDate());
        documentRestDTO.setBillNumber(documentEntity.getBillNumber());
        documentRestDTO.setStatus(documentEntity.getStatus().toString());
        documentRestDTO.setTotalAmount(documentEntity.getTotalAmount());
        documentRestDTO.setCurrencyID(documentEntity.getCurrency().getId());
        return documentRestDTO;
    }
    public Long getId() {
        return id;
    }

    @JsonCreator
    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    @JsonCreator
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getIssuanceDate() {
        return issuanceDate;
    }

    @JsonCreator
    public void setIssuanceDate(Date issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public String getBillNumber() {
        return billNumber;
    }

    @JsonCreator
    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getStatus() {
        return status;
    }

    @JsonCreator
    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    @JsonCreator
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getCurrencyID() {
        return currencyID;
    }

    @JsonCreator
    public void setCurrencyID(Long currencyID) {
        this.currencyID = currencyID;
    }
}
