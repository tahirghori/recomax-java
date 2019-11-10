package com.ms.base.workspace.service;

import com.ms.base.beam.BaseModel;
import com.ms.base.datasource.repository.BaseRepository;
import com.ms.base.throwable.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    BaseRepository<T> repository;

    @Autowired
    ResponseBuilder responseBuilder;

    @Override
    public T insert(T item) {
        try {
            return repository.save(item);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            String p =e.getMessage();
         throw    responseBuilder.setMessage(e.getMessage())
                    .setDetails("DataBase Insertion Service")
                    .buildThrow(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<T> searchByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<T> getAll() {
        return repository.findAllByDeleted(false);
    }

    @Override
    public Optional<T> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<T> findByIdOptional(UUID id) {
        return  repository.findById(id);
    }

    @Override
    public T delete(UUID id) {
        Optional<T> item = repository.findById(id);
        if (item.isPresent()) {
            repository.delete(item.get());
            return item.get();
        }
        return null;
    }
    @Override
    public List<T> saveAll(Iterable<T> list) {
        return repository.saveAll(list);
    }

    @Override
    public T deleteSoft(UUID id) {
        Optional<T> item = repository.findById(id);
        if (item.isPresent() && item.get().isDeleted() == false) {
            item.get().setDeleted(true);
            repository.save(item.get());
            return item.get();
        }
        return null;
    }

    @Override
    public T update(T item) {
        return repository.saveAndFlush(item);
    }

    @Override
    public T findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public boolean exists(String name) {
        if (repository.findByName(name) != null) {
            return true;
        }
        return false;
    }
    @Override
    public Page<T> findListPage(Pageable pageable ){
        return  repository.findAll(pageable);

    }
    @Override
    public List<T> findByNameContainingIgnoreCase(String keyword){
        return  repository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public Page<T> findAllActive(Pageable pageable) {
        return  repository.findAllByDeleted(false,pageable);
    }

}
