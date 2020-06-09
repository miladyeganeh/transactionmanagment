package com.my.accountmanager.model.dto;

import com.my.accountmanager.domain.entity.CustomerEntity;
import org.modelmapper.ModelMapper;

public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String customerNumber;
    private String email;
    private String address;

    public static CustomerEntity from(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = new ModelMapper().map(customerDTO, CustomerEntity.class);
        return customerEntity;
    }

    public static CustomerDTO to(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = new ModelMapper().map(customerEntity, CustomerDTO.class);
        return customerDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
