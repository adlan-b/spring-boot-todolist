package main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@ControllerAdvice
public class TodoGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<TodoIncorrectData> handleException(ToDoNotFoundException exception) {
        TodoIncorrectData data = new TodoIncorrectData();
        data.setInfo(exception.toString());
        data.setLocalDateTime(LocalDateTime.now());
        data.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<TodoIncorrectData> handleException(Exception exception) {
        TodoIncorrectData data = new TodoIncorrectData();
        data.setInfo(exception.toString());
        data.setLocalDateTime(LocalDateTime.now());
        data.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }


//    @ExceptionHandler
//    public ModelAndView handleException(FileStorageException exception, RedirectAttributes redirectAttributes) {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("message", exception.getMsg());
//        mav.setViewName("error");
//        return mav;
//    }
}
