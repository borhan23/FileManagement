package com.mburak.etscase.controller;

import com.mburak.etscase.exception.FileExceptionHandler;
import com.mburak.etscase.model.File;
import com.mburak.etscase.model.ResponseFile;
import com.mburak.etscase.message.ResponseMessage;
import com.mburak.etscase.services.FileService;
import com.mburak.etscase.validator.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        if(FileValidator.isSupportedContentType(file.getContentType())) {
            fileService.saveFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
        }else
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(new ResponseMessage("Unsupported file type:"));
    }

    @GetMapping
    public List<ResponseFile> getAllfiles(){
        return fileService.getAllfile();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFile> getFileAttributesById(@PathVariable Long id) {
        ResponseFile responseFile = fileService.getFileAttributesById(id);
        if (responseFile != null) {
            return new ResponseEntity<>(responseFile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/content")
    public byte[] getFileContentById(@PathVariable Long id) {
        return fileService.getFileContentById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteFile(@PathVariable long id){
        try {
            fileService.deleteFileById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Deleted the file successfully."));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Deleting file is not exist."));
        }
    }



}
