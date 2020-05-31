package com.my.accountmanager.service.implementation;

import com.my.accountmanager.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
public abstract class BaseServiceImpl<T, R extends JpaRepository<T, Long>> implements BaseService<T> {
    private R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public T save(T obj){
        repository.save(obj);
        return obj;
    }

    @Override
    public T update(T obj){
        repository.save(obj);
        return obj;
    }

    @Override
    public boolean delete(T obj){
        repository.delete(obj);
        return true;
    }

    @Override
    public Optional<T> get(Long id){
        return repository.findById(id);
    }

    @Override
    public Iterable<T> getAll(){
        return repository.findAll();
    }
}
