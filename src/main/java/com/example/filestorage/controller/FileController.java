package com.example.filestorage.controller;

import com.example.filestorage.model.FileEntity;
import com.example.filestorage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity<Long> createFile(@RequestBody FileEntity fileEntity) {
        System.out.println("Полученные данные: " + fileEntity.getFileData()); // Логирование
        FileEntity savedFile = fileService.saveFile(fileEntity);
        return ResponseEntity.ok(savedFile.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileEntity> getFileById(@PathVariable Long id) {
        Optional<FileEntity> fileEntity = fileService.getFileById(id);
        if (fileEntity.isPresent()) {
            return ResponseEntity.ok(fileEntity.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FileEntity>> getAllFiles() {
        List<FileEntity> files = fileService.getAllFiles();
        return ResponseEntity.ok(files);
    }

    @GetMapping
    public Page<FileEntity> getAllFiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sortDirection,
            @RequestParam(defaultValue = "creationDate") String sortBy) {

        return fileService.getAllFiles(page, size, sortDirection, sortBy);
    }
}
