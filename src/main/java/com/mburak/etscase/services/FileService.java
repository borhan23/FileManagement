package com.mburak.etscase.services;

import com.mburak.etscase.model.File;
import com.mburak.etscase.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
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

    public void saveFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File fileModel = new File(fileName, file.getContentType(), file.getSize(), file.getBytes());
        fileModel.setFilePath(ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString());
        fileRepository.save(fileModel);
    }

    public void deleteFileById(long id) {
        fileRepository.deleteById(id);
    }

}
