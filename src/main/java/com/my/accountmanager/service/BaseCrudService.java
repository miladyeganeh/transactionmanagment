package com.my.accountmanager.service;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
public interface BaseCrudService<T> {

    public T save(T obj);

    public T update(T obj);

    public boolean delete(T obj);

    public Optional<T> getById(Long id);

    public Iterable<T> getAll();
}