package com.my.accountmanager.controller;

import com.my.accountmanager.model.dto.CustomerDTO;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    @Test
    void getCard_ifFound() {
        CustomerDTO mockedCustomer = new CustomerDTO();
        mockedCustomer.setId(1L);
        when(this.customerService.getCustomerByID(any())).thenReturn(Optional.of(mockedCustomer));
        ResponseEntity<ResponseDTO<CustomerDTO>> response = customerController.getCustomer(1L);
        assertThat(response.getStatusCode().value(), is(HttpStatus.OK.value()));
        assertThat(Objects.requireNonNull(response.getBody()).getData().getId(), is(mockedCustomer.getId()));
    }

    @Test
    void getCard_ifNotFound() {
        when(this.customerService.getCustomerByID(any())).thenReturn(Optional.empty());
        ResponseEntity<ResponseDTO<CustomerDTO>> response = customerController.getCustomer(1L);
        assertThat(response.getStatusCode().value(), is(HttpStatus.NOT_FOUND.value()));
    }
}