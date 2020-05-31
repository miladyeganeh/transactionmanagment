package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.CustomerEntity;
import com.my.accountmanager.repository.CustomerRepository;
import com.my.accountmanager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Service
public class CustomerServiceImpl extends BaseServiceImpl<CustomerEntity, CustomerRepository> implements CustomerService {
    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }
}
