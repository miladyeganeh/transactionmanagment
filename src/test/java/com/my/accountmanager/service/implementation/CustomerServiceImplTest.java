package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.CustomerEntity;
import com.my.accountmanager.model.dto.CustomerDTO;
import com.my.accountmanager.repository.CustomerRepository;
import com.my.accountmanager.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
class CustomerServiceImplTest {

    @MockBean
    private CustomerRepository customerRepository;
    private final CustomerService customerService;
    private CustomerEntity mockedEntity;

    @Autowired
    public CustomerServiceImplTest(CustomerService customerService) {
        this.customerService = customerService;
    }

    @BeforeEach
    void setUp() {
        mockedEntity = createMockCustomerEntity();
    }

    @Test
    void getCustomerByID() {
        Mockito.when(customerRepository.findById(any())).thenReturn(Optional.of(mockedEntity));
        Optional<CustomerDTO> obtainedCustomerDTO = customerService.getCustomerByID(1L);
        CustomerDTO mockedCustomerDTO = CustomerDTO.from(mockedEntity);
        assertThat(obtainedCustomerDTO.isEmpty(), is(false));
        assertThat(obtainedCustomerDTO.get().getCustomerNumber(), is(mockedCustomerDTO.getCustomerNumber()));
    }

    @Test
    void createOrSaveCustomer() {
        Mockito.when(customerRepository.save(any())).thenReturn(mockedEntity);
        CustomerDTO customerDTO = CustomerDTO.from(mockedEntity);
        Optional<CustomerDTO> obtainedCustomer = customerService.createCustomer(customerDTO);
        assertThat(obtainedCustomer.isEmpty(), is(false));
        assertThat(obtainedCustomer.get().getCustomerNumber(), is(mockedEntity.getCustomerNumber()));
    }

    private CustomerEntity createMockCustomerEntity() {
        CustomerEntity mockedCustomer = new CustomerEntity();
        mockedCustomer.setId(1L);
        mockedCustomer.setEmail("test@test.com");
        mockedCustomer.setFirstName("Milad");
        mockedCustomer.setLastName("Yeganeh");
        mockedCustomer.setCustomerNumber("123345");
        return mockedCustomer;
    }
}