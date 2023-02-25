package com.mburak.etscase.services;

import com.mburak.etscase.model.File;
import com.mburak.etscase.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<File> getAllfile() {
        return fileRepository.findAll();
    }

    public File getFileById(long id) {
        return fileRepository.getReferenceById(id);
    }

    public void saveFile(File File) {

        fileRepository.save(File);
    }

    public void deleteFileById(long id) {
        fileRepository.deleteById(id);
    }

}
