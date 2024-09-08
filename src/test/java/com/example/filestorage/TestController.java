package com.example.filestorage;


import com.example.filestorage.controller.FileController;
import com.example.filestorage.model.FileEntity;
import com.example.filestorage.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestController {

    @Mock
    private FileService fileService;

    @InjectMocks
    private FileController fileController;

    private FileEntity fileEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        fileEntity = new FileEntity();
        fileEntity.setId(1L);
        fileEntity.setTitle("Test File");
        fileEntity.setDescription("Test Description");
        fileEntity.setFileData("TestData");
        fileEntity.setCreationDate(LocalDateTime.now());
    }

    @org.junit.jupiter.api.Test
    public void testCreateFile() {
        when(fileService.saveFile(fileEntity)).thenReturn(fileEntity);

        ResponseEntity<Long> response = fileController.createFile(fileEntity);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody());
        verify(fileService, times(1)).saveFile(fileEntity);
    }

    @org.junit.jupiter.api.Test
    public void testGetFileById_Found() {
        when(fileService.getFileById(1L)).thenReturn(Optional.of(fileEntity));

        ResponseEntity<FileEntity> response = fileController.getFileById(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(fileEntity, response.getBody());
        verify(fileService, times(1)).getFileById(1L);
    }

    @org.junit.jupiter.api.Test
    public void testGetFileById_NotFound() {
        when(fileService.getFileById(1L)).thenReturn(Optional.empty());

        ResponseEntity<FileEntity> response = fileController.getFileById(1L);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(fileService, times(1)).getFileById(1L);
    }

    @org.junit.jupiter.api.Test
    public void testGetAllFiles() {
        List<FileEntity> files = new ArrayList<>();
        files.add(fileEntity);
        when(fileService.getAllFiles()).thenReturn(files);

        ResponseEntity<List<FileEntity>> response = fileController.getAllFiles();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(fileService, times(1)).getAllFiles();
    }

    @org.junit.jupiter.api.Test
    public void testGetAllFilesWithPagination() {
        Page<FileEntity> page = new PageImpl<>(List.of(fileEntity));
        when(fileService.getAllFiles(anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(page);

        Page<FileEntity> result = fileController.getAllFiles(0, 10, "desc", "creationDate");

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(fileService, times(1)).getAllFiles(0, 10, "desc", "creationDate");
    }
}
