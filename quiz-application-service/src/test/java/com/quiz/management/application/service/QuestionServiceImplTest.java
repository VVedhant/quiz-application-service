package com.quiz.management.application.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.management.application.dto.QuestionDTO;
import com.quiz.management.application.entity.QuestionEntity;
import com.quiz.management.application.entity.QuizEntity;
import com.quiz.management.application.handler.QuestionException;
import com.quiz.management.application.repository.QuestionRepository;
import com.quiz.management.application.repository.QuizRepository;
import com.quiz.management.application.utility.QuestionTestSetUpUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceImplTest{

    @Mock
    QuizRepository quizRepository;

    @Mock
    ObjectMapper objectMapper;

    @Mock
    QuestionRepository questionRepository;

    @InjectMocks
    QuestionServiceImpl questionService;

    @Test
    void test_createQuestion() throws QuestionException {

        QuestionEntity question = QuestionTestSetUpUtility.setUpQuestionEntity();
        QuizEntity quiz = QuestionTestSetUpUtility.setUpQuizEntity();
        QuestionDTO questionDTO = QuestionTestSetUpUtility.setUpQuestionDTO();

        quiz.setQuestions(Arrays.asList(question));

        Mockito.when(objectMapper.convertValue(Mockito.any(QuestionEntity.class), Mockito.eq(QuestionDTO.class))).thenReturn(questionDTO);
        Mockito.when(objectMapper.convertValue(Mockito.any(QuestionDTO.class), Mockito.eq(QuestionEntity.class))).thenReturn(question);
        Mockito.when(quizRepository.findByCategory(Mockito.any())).thenReturn(Optional.of(quiz));
        Mockito.when(questionRepository.save(Mockito.any())).thenReturn(question);
        questionService.createQuestion(questionDTO);
        Mockito.verify(questionRepository , Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void test_getAllQuestions(){
        QuestionEntity question = QuestionTestSetUpUtility.setUpQuestionEntity();
        QuizEntity quiz = QuestionTestSetUpUtility.setUpQuizEntity();

        quiz.setQuestions(new ArrayList<>(Arrays.asList(question)));

        Mockito.when(questionRepository.findAll()).thenReturn(Collections.<QuestionEntity>singletonList(question));
        questionService.getAllQuestions();
        Mockito.verify(questionRepository , Mockito.times(1)).findAll();
    }

    @Test
    void test_getQuestionById() throws QuestionException {
        QuestionEntity question = QuestionTestSetUpUtility.setUpQuestionEntity();
        QuizEntity quiz = QuestionTestSetUpUtility.setUpQuizEntity();

        quiz.setQuestions(new ArrayList<>(Arrays.asList(question)));

        Mockito.when(questionRepository.findById(Mockito.any())).thenReturn(Optional.of(question));
        questionService.getQuestionById(question.getId());
        Mockito.verify(questionRepository ,Mockito.times(1)).findById(Mockito.any());
    }

    @Test
    void test_deleteQuestionById(){
        questionRepository.deleteById(1);
        Mockito.verify(questionRepository, Mockito.times(1)).deleteById(1);
    }

    @Test
    void test_updateQuestionById(){
        QuestionEntity question = QuestionTestSetUpUtility.setUpQuestionEntity();
        QuestionDTO questionDTO = QuestionTestSetUpUtility.setUpQuestionDTO();
//        Mockito.when(questionRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(question));
        Mockito.when(questionRepository.save(question)).thenReturn(question);
        questionRepository.save(question);
    }
}