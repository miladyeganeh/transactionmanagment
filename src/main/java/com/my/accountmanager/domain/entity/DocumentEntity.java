package com.my.accountmanager.domain.entity;

import com.my.accountmanager.domain.enums.DocumentStatus;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "DOCUMENT")
public class DocumentEntity extends Auditable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "ISSUANCE_DATE", nullable = false)
    private Date issuanceDate;

    @Column(name = "BILL_NUMBER", nullable = false, unique = true)
    private String billNumber;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentStatus status;

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private Double totalAmount;

//    @OneToOne(cascade = CascadeType.ALL)
//    private TransactionEntity transaction;

    @OneToOne(cascade = CascadeType.ALL)
    private DocumentEntity reversDocument;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private Set<DocumentItemEntity> documentItems;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENCY_ID")
    private CurrencyEntity currency;

    public DocumentEntity() {
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

    public Date getIssuanceDate() {
        return issuanceDate;
    }

    public void setIssuanceDate(Date issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public void setStatus(DocumentStatus status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

//    public TransactionEntity getTransaction() {
//        return transaction;
//    }
//
//    public void setTransaction(TransactionEntity transaction) {
//        this.transaction = transaction;
//    }

    public DocumentEntity getReversDocument() {
        return reversDocument;
    }

    public void setReversDocument(DocumentEntity reversDocument) {
        this.reversDocument = reversDocument;
    }

    public Set<DocumentItemEntity> getDocumentItems() {
        return documentItems;
    }

    public void setDocumentItems(Set<DocumentItemEntity> documentItems) {
        this.documentItems = documentItems;
    }

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
    }
}
