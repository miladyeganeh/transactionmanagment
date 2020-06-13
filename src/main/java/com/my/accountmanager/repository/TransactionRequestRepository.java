package com.my.accountmanager.repository;

import com.my.accountmanager.domain.entity.TransactionRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Repository
public interface TransactionRequestRepository extends JpaRepository<TransactionRequestEntity, Long> {
    Optional<TransactionRequestEntity> findByTrxID(String trxId);
}
