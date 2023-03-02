package com.mburak.etscase.validator;

public class FileValidator {

    public static boolean isSupportedContentType(String contentType) {
        return contentType.equals(FileType.DOCX_MIME_TYPE)
                || contentType.equals(FileType.XLSX_MIME_TYPE)
                || contentType.equals(FileType.PDF_MIME_TYPE)
                || contentType.equals(FileType.PNG_MIME_TYPE)
                || contentType.equals(FileType.JPEG_MIME_TYPE);
    }
}
