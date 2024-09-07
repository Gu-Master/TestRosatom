package com.example.filestorage.service;

import com.example.filestorage.model.FileEntity;
import com.example.filestorage.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileEntity saveFile(FileEntity fileEntity) {
        FileEntity savedFile = fileRepository.save(fileEntity);
        return savedFile;
    }

    public Optional<FileEntity> getFileById(Long id) {
        return fileRepository.findById(id);
    }

    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }

    public Page<FileEntity> getAllFiles(int page, int size, String sortDirection, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return fileRepository.findAll(pageable);
    }


}
