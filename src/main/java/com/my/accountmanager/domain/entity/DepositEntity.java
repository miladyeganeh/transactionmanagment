package com.my.accountmanager.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "DEPOSIT")
public class DepositEntity extends Auditable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "DEPOSIT_NUMBER", nullable = false)
    private String depositNumber;

    @Column(name = "IS_DEFAULT", nullable = false)
    private boolean isDefault;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    @JsonIgnore
    private AccountEntity account;

    @OneToMany(mappedBy = "deposit", cascade = CascadeType.ALL)
    @Column(name = "TRANSACTION", nullable = false)
    private Set<TransactionEntity> transaction;

    public DepositEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public Set<TransactionEntity> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<TransactionEntity> transaction) {
        this.transaction = transaction;
    }
}
