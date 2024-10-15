package com.quiz.management.application.handler;

import com.quiz.management.application.entity.QuizEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QuizManagementExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }


    @ExceptionHandler(QuestionException.class)
    public ResponseEntity<String> questionException(QuestionException questionException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(questionException.getMessage());
    }

    @ExceptionHandler(QuizException.class)
    public ResponseEntity<String> quizException(QuizException quizException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(quizException.getMessage());
    }


}
