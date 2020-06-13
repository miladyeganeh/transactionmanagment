package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.CustomerEntity;
import com.my.accountmanager.model.dto.CustomerDTO;
import com.my.accountmanager.repository.CustomerRepository;
import com.my.accountmanager.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Service
public class CustomerServiceImpl extends BaseCrudServiceImpl<CustomerEntity, CustomerRepository> implements CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<CustomerDTO> getCustomerByID(Long id) {
        logger.debug(":::::Start getCustomerByID, id: " + id);
        Optional<CustomerEntity> customerEntity = super.findById(id);
        logger.debug(":::::Finish getCustomerByID");
        return customerEntity.map(CustomerDTO::from);
    }

    @Override
    public Optional<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
        logger.debug(":::::Start createCustomer, email: " + customerDTO.getEmail());
        CustomerEntity savedCustomer = save(CustomerDTO.to(customerDTO));
        logger.debug(":::::Finish createCustomer");
        return Optional.of(CustomerDTO.from(savedCustomer));
    }

    @Override
    public Optional<CustomerDTO> updateCustomer(CustomerDTO customerDTO) {
        logger.debug(":::::Start updateCustomer, id: " + customerDTO.getNationalId());
        Optional<CustomerEntity> customerEntity = this.customerRepository.findByNationalId(customerDTO.getNationalId());
        customerEntity.ifPresent(customer -> {
            customer.setAddress(customerDTO.getAddress());
            customer.setEmail(customerDTO.getEmail());
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            save(customer);
        });
        logger.debug(":::::Finish updateCustomer");
        return customerEntity.map(CustomerDTO::from);
    }


    @Override
    public void deleteCustomer(Long id) {
        logger.debug(":::::Start deleteCustomer, id: " + id);
        Optional<CustomerEntity> customerEntity = findById(id);
        customerEntity.ifPresent(this::delete);
        logger.debug(":::::Finish deleteCustomer");
    }
}
