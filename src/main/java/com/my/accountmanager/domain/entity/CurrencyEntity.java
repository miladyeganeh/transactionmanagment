package com.my.accountmanager.domain.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CURRENCY")
public class CurrencyEntity extends Auditable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "CODE", unique = true)
    private String code;

    @Column(name = "NAME")
    private String name;

    public CurrencyEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
