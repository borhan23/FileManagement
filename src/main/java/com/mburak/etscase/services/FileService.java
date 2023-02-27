package com.mburak.etscase.services;

import com.mburak.etscase.model.File;
import com.mburak.etscase.model.ResponseFile;
import com.mburak.etscase.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileService {
    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<ResponseFile> getAllfile() {
        List<File> files = fileRepository.findAll();
        return files.stream().map(this::mapToFileResponse)
                .collect(Collectors.toList());
    }

    public byte[] getFileContentById(Long id) {
        Optional<File> fileOptional = fileRepository.findById(id);
        if (fileOptional.isPresent()){
            return mapToFileResponse(fileOptional.get()).getFileData();
        } else {
            return null;
        }
    }

    public ResponseFile getFileAttributesById(Long id) {
        Optional<File> fileOptional = fileRepository.findById(id);
        if (fileOptional.isPresent()) {
            return mapToFileResponse(fileOptional.get());
        } else {
            return null;
        }
    }

    public void saveFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File fileModel = new File(fileName, file.getContentType(), file.getSize(), file.getBytes());
        fileModel.setFilePath(ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName).toUriString());
        fileRepository.save(fileModel);
    }

    public void deleteFileById(long id) {
        fileRepository.deleteById(id);
    }

    private ResponseFile mapToFileResponse(File file) {
        ResponseFile fileResponse = new ResponseFile(file.getFileName(), file.getFileType(), file.getFileSize(), file.getFilePath(), file.getFileData());
        fileResponse.setId(file.getId());
        fileResponse.setFileName(file.getFileName());
        fileResponse.setFileType(file.getFileType());
        fileResponse.setFileSize(file.getFileSize());
        fileResponse.setFilePath(file.getFilePath());
        fileResponse.setFileData(file.getFileData());

        return fileResponse;
    }

    public File replaceFileById(long id, MultipartFile file) {
        Optional<File> fileOptional = fileRepository.findById(id);
        if (fileOptional.isPresent()) {
            File fileEntity = fileOptional.get();
            fileEntity.setFileName(file.getOriginalFilename());
            fileEntity.setFileSize(file.getSize());
            fileEntity.setFileType(file.getContentType());
            fileEntity.setFilePath(ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(file.getOriginalFilename()).toUriString());
            return fileRepository.save(fileEntity);
        } else {
            return null;
        }
    }
}
