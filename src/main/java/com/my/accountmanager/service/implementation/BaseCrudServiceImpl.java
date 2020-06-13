package com.my.accountmanager.service.implementation;

import com.my.accountmanager.service.BaseCrudService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
public abstract class BaseCrudServiceImpl<T, R extends JpaRepository<T, Long>> implements BaseCrudService<T> {
    protected R repository;

    public BaseCrudServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public T save(T obj) {
        return repository.save(obj);
    }

    @Override
    public T update(T obj){
        return repository.save(obj);
    }

    @Override
    public boolean delete(T obj){
        repository.delete(obj);
        return true;
    }

    @Override
    public Optional<T> findById(Long id){
        return repository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }
}
