package com.ms.base.datasource.repository;


import com.ms.base.beam.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T extends Base> extends JpaRepository<T, UUID> {
    T findByName(String name);
    List<T> findByNameContainingIgnoreCase(String keyword);
}
