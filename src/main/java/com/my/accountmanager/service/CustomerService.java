package com.my.accountmanager.service;

import com.my.accountmanager.domain.entity.CustomerEntity;
import com.my.accountmanager.model.dto.CustomerDTO;

import java.util.Optional;

public interface CustomerService extends BaseCrudService<CustomerEntity> {
    Optional<CustomerDTO> getCustomerByID(Long id);
    Optional<CustomerDTO> createCustomer(CustomerDTO customerDTO);
    Optional<CustomerDTO> updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long id);
}
