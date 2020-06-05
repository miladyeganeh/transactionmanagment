package com.my.accountmanager.domain.entity;

import com.my.accountmanager.domain.enums.DocumentStatus;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "DOCUMENT")
public class DocumentEntity extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "ISSUANCE_DATE", nullable = false)
    private Date issuanceDate;

    @Column(name = "BILL_NUMBER", nullable = false)
    private String billNumber;

    @Column(name = "STATUS", nullable = false)
    private DocumentStatus status;

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private Double totalAmount;

    @OneToOne
    private TransactionEntity transaction;

    @OneToOne
    private DocumentEntity reversDocument;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private Set<DocumentItemEntity> documentItems;

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

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }

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
}
