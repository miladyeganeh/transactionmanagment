package com.my.accountmanager.repository;

import com.my.accountmanager.domain.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
}
