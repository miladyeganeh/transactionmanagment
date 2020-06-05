package com.my.accountmanager.repository;

import com.my.accountmanager.domain.entity.DocumentItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Repository
public interface DocumentItemRepository extends JpaRepository<DocumentItemEntity, Long> {
}
