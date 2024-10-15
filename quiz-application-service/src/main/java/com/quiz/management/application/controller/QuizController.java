package com.quiz.management.application.controller;

import com.quiz.management.application.dto.QuizDTO;
import com.quiz.management.application.handler.QuizException;
import com.quiz.management.application.service.QuizService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quizzes")
@Slf4j
public class QuizController {


    @Autowired
    private QuizService quizService;


    @PostMapping
    public ResponseEntity<QuizDTO> createQuiz(@Valid @RequestBody QuizDTO quizDTO) {
        log.info("Creating quiz with category: {}", quizDTO.getCategory());
        QuizDTO createdQuizDTO = quizService.createQuiz(quizDTO);
        return new ResponseEntity<>(createdQuizDTO, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<QuizDTO>> getAllQuiz() {
        log.info("get All Quizes");
        List<QuizDTO> quizDTOs = quizService.getAllQuizzes();
        return new ResponseEntity<>(quizDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> getQuiz(@PathVariable Integer id) throws QuizException {
        log.info("Getting quiz with id: {}", id);
        QuizDTO quizDTO = quizService.getQuizById(id);
        return ResponseEntity.ok(quizDTO);

    }


    @DeleteMapping("/{qid}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Integer qid) {
        log.info("deleted quiz by id {}", qid);
        quizService.deleteQuizById(qid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        quizService.delete();
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<QuizDTO> updateQuiz(QuizDTO quizDTO) {
        log.info("Quiz has been updated");
        QuizDTO updatedQuizDTO = quizService.updateQuiz(quizDTO);
        return ResponseEntity.ok(updatedQuizDTO);
    }
}