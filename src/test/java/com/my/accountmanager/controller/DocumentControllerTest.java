package com.my.accountmanager.controller;

import com.my.accountmanager.domain.entity.DocumentEntity;
import com.my.accountmanager.model.dto.response.ResponseDTO;
import com.my.accountmanager.model.dto.response.withrel.DocumentRestDTO;
import com.my.accountmanager.service.DocumentService;
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
class DocumentControllerTest {

    @Autowired
    private DocumentController documentController;

    @MockBean
    private DocumentService documentService;

    @Test
    void getDocument_ifFound() {
        DocumentEntity mockedDocument = new DocumentEntity();
        mockedDocument.setId(1L);
        when(this.documentService.findById(any())).thenReturn(Optional.of(mockedDocument));
        ResponseEntity<ResponseDTO<DocumentRestDTO>> response = documentController.getDocument(1L);
        assertThat(response.getStatusCode().value(), is(HttpStatus.OK.value()));
        assertThat(Objects.requireNonNull(response.getBody()).getData().getId(), is(mockedDocument.getId()));
    }

    @Test
    void getDocument_ifNotFound() {
        when(this.documentService.findById(any())).thenReturn(Optional.empty());
        ResponseEntity<ResponseDTO<DocumentRestDTO>> response = documentController.getDocument(1L);
        assertThat(response.getStatusCode().value(), is(HttpStatus.NOT_FOUND.value()));
    }
}