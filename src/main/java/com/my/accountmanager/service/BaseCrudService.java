package com.my.accountmanager.service;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
public interface BaseCrudService<T> {
     T save(T obj);
     T update(T obj);
     boolean delete(T obj);
     Optional<T> findById(Long id);
     Iterable<T> getAll();
}