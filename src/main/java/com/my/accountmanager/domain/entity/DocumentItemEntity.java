package com.my.accountmanager.domain.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "DOCUMENT_ITEM")
public class DocumentItemEntity extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "COMMENT", nullable = false)
    private String comment;

    @Column(name = "ISSUANCE_NAME", nullable = false)
    private Date issuanceDate;

    @Column(name = "AMOUNT")
    private Double amount;

    @ManyToOne
    private DocumentEntity document;

    public Date getIssuanceDate() {
        return issuanceDate;
    }

    public void setIssuanceDate(Date issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public DocumentItemEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }
}
