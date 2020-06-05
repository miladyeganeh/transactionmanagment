package com.my.accountmanager.repository;

import com.my.accountmanager.domain.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {
}
