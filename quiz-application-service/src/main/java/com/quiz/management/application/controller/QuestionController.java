package com.quiz.management.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.management.application.dto.QuestionDTO;
import com.quiz.management.application.dto.QuizDTO;
import com.quiz.management.application.entity.QuestionEntity;
import com.quiz.management.application.entity.QuizEntity;
import com.quiz.management.application.handler.QuestionException;
import com.quiz.management.application.service.QuestionService;
import com.quiz.management.application.service.QuestionServiceImpl;
import com.quiz.management.application.service.QuizService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("questions")
@Slf4j
public class QuestionController {


    @Autowired
    QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionDTO questionDTO) throws QuestionException {
        log.info("Create question with category: {}", questionDTO.getCategory());
        return new ResponseEntity<>(questionService.createQuestion(questionDTO), HttpStatus.CREATED);
    }


    @GetMapping
    public Iterable<QuestionDTO> getAllQuestions() {
        log.info("Fetched all questions from quiz");
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getQuestionWithId(@PathVariable Integer id) throws QuestionException {
        log.info("Getting the question with id: {}", id);
        return new ResponseEntity<>(questionService.getQuestionById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable Integer id, @Valid @RequestBody QuestionDTO updatedQuestionDTO) throws QuestionException {
        return new ResponseEntity<QuestionDTO>(questionService.updateQuestionById(id, updatedQuestionDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("questionId") Integer id) throws QuestionException {
        questionService.deleteQuestionById(id);
        log.info("Deleting the quiz with id: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}