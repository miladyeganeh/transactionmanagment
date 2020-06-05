package com.my.accountmanager.domain.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CURRENCY")
public class CurrencyEntity extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "CODE")
    private Integer code;

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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
