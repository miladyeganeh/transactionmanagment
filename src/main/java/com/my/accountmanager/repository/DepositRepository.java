package com.my.accountmanager.repository;

import com.my.accountmanager.domain.DepositEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<DepositEntity, Long> {
}
