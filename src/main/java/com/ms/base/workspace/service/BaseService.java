package com.ms.base.workspace.service;

import com.ms.base.beam.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseService<T extends Base> {
    public T insert(T item);
    public List<T> findAll();
    public List<T> searchByName(String keyword);
    public List<T> saveAll(Iterable<T> list);
    public List<T> getAll();
//    public T findById(UUID id);
    public Optional<T> findById(UUID id);
    public Optional<T> findByIdOptional(UUID id);
    public T delete(UUID id);
    public T deleteSoft(UUID id);
    public T update(T item);
    public T findByName(String name);
    boolean exists(String name);
    public Page<T> findListPage(Pageable pageable);
    List<T> findByNameContainingIgnoreCase(String keyword);
    public Page<T> findAllActive(Pageable pageable);
}
