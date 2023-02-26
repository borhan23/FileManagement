package com.mburak.etscase.controller;

import com.mburak.etscase.exception.FileExceptionHandler;
import com.mburak.etscase.model.ResponseFile;
import com.mburak.etscase.message.ResponseMessage;
import com.mburak.etscase.services.FileService;
import com.mburak.etscase.validator.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

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
            fileService.saveFile(file);
        if(FileValidator.isSupportedContentType(file.getContentType()))
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
        else
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(new ResponseMessage("Unsupported file type:"));
    }

    @GetMapping("/")
    public List<ResponseFile> getAllfile(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFileById(@PathVariable long id) {
        return null;
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> getFileById(@PathVariable String fileName) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteFile(@PathVariable long id){
        return null;
    }

}
