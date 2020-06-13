package com.my.accountmanager.model.dto;

import com.my.accountmanager.domain.entity.CustomerEntity;

import java.io.Serializable;

public class CustomerDTO implements Serializable {

    private Long id;
    private String nationalId;
    private String firstName;
    private String lastName;
    private String customerNumber;
    private String email;
    private String address;

    public static CustomerEntity to(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerDTO.getId());
        customerEntity.setNationalId(customerDTO.getNationalId());
        customerEntity.setCustomerNumber(customerDTO.getCustomerNumber());
        customerEntity.setFirstName(customerDTO.getFirstName());
        customerEntity.setLastName(customerDTO.getLastName());
        customerEntity.setEmail(customerDTO.getEmail());
        return customerEntity;
    }

    public static CustomerDTO from(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customerEntity.getId());
        customerDTO.setNationalId(customerEntity.getNationalId());
        customerDTO.setCustomerNumber(customerEntity.getCustomerNumber());
        customerDTO.setFirstName(customerEntity.getFirstName());
        customerDTO.setLastName(customerEntity.getLastName());
        customerDTO.setEmail(customerEntity.getEmail());
        return customerDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
