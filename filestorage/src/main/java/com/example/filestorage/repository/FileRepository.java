package com.example.filestorage.repository;

import com.example.filestorage.model.FileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    Page<FileEntity> findAll(Pageable pageable);
}
