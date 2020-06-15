package com.my.accountmanager.service;

import java.util.List;
import java.util.Optional;

public interface BaseCrudService<T> {
     T save(T obj);
     T update(T obj);
     boolean delete(T obj);
     Optional<T> findById(Long id);
     List<T> getAll();
}
