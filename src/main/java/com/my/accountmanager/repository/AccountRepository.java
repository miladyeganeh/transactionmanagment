package com.my.accountmanager.repository;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
    Optional<AccountEntity> findByAccountNumberAndIsActiveAndStatus(String accountNumber, boolean isActive, AccountStatus status);
}
