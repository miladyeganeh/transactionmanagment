package com.my.accountmanager.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "DOCUMENT")
public class DocumentEntity extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "COMMENT")
    private String comment;

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
