package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.entity.DepositEntity;
import com.my.accountmanager.domain.entity.TransactionEntity;
import com.my.accountmanager.model.dto.DepositDTO;
import com.my.accountmanager.repository.DepositRepository;
import com.my.accountmanager.service.DepositService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
class DepositServiceImplTest {

    @MockBean
    private DepositRepository depositRepository;
    private final DepositService depositService;
    private DepositEntity mockedDepositEntity;

    @Autowired
    public DepositServiceImplTest(DepositService depositService) {
        this.depositService = depositService;
    }

    @BeforeEach
    void setUp() {
        mockedDepositEntity = createMockDepositEntity();
    }

    @Test
    void getByDepositNumber_if_found() {
        Mockito.when(depositRepository.getByDepositNumber(any())).thenReturn(mockedDepositEntity);
        Optional<DepositDTO> obtainedEntity = this.depositService.getByDepositNumber(any());
        assertThat(obtainedEntity.isEmpty(), is(false));
        assertThat(obtainedEntity.get().getDepositNumber(), is(mockedDepositEntity.getDepositNumber()));
    }

    @Test
    void getByDepositNumber_If_Not_Found() {
        Mockito.when(depositRepository.getByDepositNumber(any())).thenReturn(null);
        Optional<DepositDTO> obtainedEntity = this.depositService.getByDepositNumber(any());
        assertThat(obtainedEntity.isEmpty(), is(true));
    }

    @Test
    void createDeposit_If_Created_Successfully() {
        Mockito.when(depositRepository.save(any())).thenReturn(mockedDepositEntity);
        Optional<DepositDTO> obtainedEntity = this.depositService.createDeposit(DepositDTO.to(mockedDepositEntity));
        assertThat(obtainedEntity.isEmpty(), is(false));
        assertThat(obtainedEntity.get().getDepositNumber(), is(mockedDepositEntity.getDepositNumber()));
    }

    @Test
    void createDeposit_If_Created_Fail() {
        Mockito.when(depositRepository.save(any())).thenReturn(null);
        Optional<DepositDTO> obtainedEntity = this.depositService.createDeposit(DepositDTO.to(mockedDepositEntity));
        assertThat(obtainedEntity.isEmpty(), is(true));
    }

    @Test
    void updateDeposit_If_Found_Deposit() {
        Mockito.when(depositRepository.getByDepositNumber(any())).thenReturn(mockedDepositEntity);
        Optional<DepositDTO> obtainedEntity = this.depositService.updateDeposit(DepositDTO.to(mockedDepositEntity));
        assertThat(obtainedEntity.isEmpty(), is(false));
        assertThat(obtainedEntity.get().getIsActive(), is(mockedDepositEntity.getIsActive()));
        assertThat(obtainedEntity.get().getIsDefault(), is(mockedDepositEntity.getIsDefault()));
        assertThat(obtainedEntity.get().getDepositNumber(), is(mockedDepositEntity.getDepositNumber()));
    }

    @Test
    void updateDeposit_If_Not_Found_Deposit () {
        Mockito.when(depositRepository.getByDepositNumber(any())).thenReturn(null);
        Optional<DepositDTO> obtainedEntity = this.depositService.updateDeposit(DepositDTO.to(mockedDepositEntity));
        assertThat(obtainedEntity.isEmpty(), is(true));
    }

    private DepositEntity createMockDepositEntity() {
        DepositEntity depositEntity = new DepositEntity();
        depositEntity.setId(1L);
        depositEntity.setIsActive(true);
        depositEntity.setDepositNumber("200.1.1234.1");
        depositEntity.setIsDefault(true);
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(22L);
        depositEntity.setAccount(accountEntity);
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionID("test_trx_id");
        transactionEntity.setId(1L);
        transactionEntity.setSourceAccount(accountEntity);
        Set<TransactionEntity> transactions = new HashSet<>();
        transactions.add(transactionEntity);
        depositEntity.setTransaction(transactions);
        return depositEntity;
    }
}