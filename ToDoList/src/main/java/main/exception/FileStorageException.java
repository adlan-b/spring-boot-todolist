package main.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class FileStorageException {
    private static final long serialVersionUID = 1L;
    private String msg;

    public FileStorageException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
