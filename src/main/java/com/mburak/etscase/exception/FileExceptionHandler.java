package com.mburak.etscase.exception;

import com.mburak.etscase.message.ResponseMessage;
import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileExceptionHandler extends ResponseEntityExceptionHandler {

    /*
    If the file's size will be bigger than 5mb, API will be return the 417 error code.
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File must be lower than 5 MB!"));
    }

    /*
    If the file's extensions won't be 'png, jpeg, jpg, docx, pdf, xlsx', API will be return the 415 error code.
     */
    @ExceptionHandler(InvalidContentTypeException.class)
    protected ResponseEntity<Object> handleNotSupportedFileType(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(new ResponseMessage("Unsupported file type:"));
    }
}
