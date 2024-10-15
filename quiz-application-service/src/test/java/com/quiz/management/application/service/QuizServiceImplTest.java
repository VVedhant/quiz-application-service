package com.quiz.management.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.management.application.dto.QuizDTO;
import com.quiz.management.application.entity.QuizEntity;
import com.quiz.management.application.handler.QuizException;
import com.quiz.management.application.repository.QuizRepository;
import com.quiz.management.application.utility.QuizTestSetUpUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class QuizServiceImplTest {
    @Mock
    QuizRepository quizRepository;

    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    QuizServiceImpl quizService;


    @Test
    void test_createQuiz_Created() {

        QuizDTO quizDTO = QuizTestSetUpUtility.setUpQuizDTO();
        QuizEntity quizEntity = QuizTestSetUpUtility.setUpQuizEntity();

        Mockito.when(objectMapper.convertValue(Mockito.any(QuizEntity.class), Mockito.eq(QuizDTO.class))).thenReturn(quizDTO);
        Mockito.when(objectMapper.convertValue(Mockito.any(QuizDTO.class), Mockito.eq(QuizEntity.class))).thenReturn(quizEntity);
        Mockito.when(quizRepository.findByCategory(Mockito.anyString())).thenReturn(Optional.of(quizEntity));
        Mockito.when(quizRepository.save(Mockito.any())).thenReturn(Mockito.any());
        quizService.createQuiz(quizDTO);
        Mockito.verify(quizRepository, Mockito.atLeastOnce()).findByCategory(Mockito.anyString());
        Mockito.verify(quizRepository, Mockito.times(1)).save(Mockito.any());
    }


    @Test
    void getQuizById() throws QuizException {

        QuizDTO quizDTO = QuizTestSetUpUtility.setUpQuizDTO();
        QuizEntity quizEntity = QuizTestSetUpUtility.setUpQuizEntity();

        Mockito.when(objectMapper.convertValue(Mockito.any(QuizEntity.class), Mockito.eq(QuizDTO.class))).thenReturn(quizDTO);
//        Mockito.when(objectMapper.convertValue(Mockito.any(QuizDTO.class), Mockito.eq(QuizEntity.class))).thenReturn(quizEntity);
        Mockito.when(quizRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(quizEntity));
        quizService.getQuizById(1);
        Mockito.verify(quizRepository, Mockito.times(1)).findById(Mockito.anyInt());
    }


    @Test
    void test_deleteQuizById(){
        quizService.deleteQuizById(Mockito.anyInt());
        Mockito.verify(quizRepository, Mockito.atLeastOnce()).deleteById(Mockito.anyInt());
    }


    @Test
    void test_deleteAllQuiz(){
        quizService.delete();
        Mockito.verify(quizRepository, Mockito.atLeastOnce()).deleteAll();
    }


    @Test
    void test_getAllQuizzes(){
        QuizDTO quizDTO = QuizTestSetUpUtility.setUpQuizDTO();
        QuizEntity quizEntity = QuizTestSetUpUtility.setUpQuizEntity();

        List<QuizEntity> quizEntityList = List.of(quizEntity);

        Mockito.when(objectMapper.convertValue(Mockito.any(QuizEntity.class), Mockito.eq(QuizDTO.class))).thenReturn(quizDTO);
//        Mockito.when(objectMapper.convertValue(Mockito.any(QuizDTO.class), Mockito.eq(QuizEntity.class))).thenReturn(quizEntity);
        Mockito.when(quizRepository.findAll()).thenReturn(quizEntityList);
        quizService.getAllQuizzes();
        Mockito.verify(quizRepository, Mockito.times(1)).findAll();
    }

//Update Quiz
    @Test
    void test_updateQuiz(){
        QuizDTO quizDTO = QuizTestSetUpUtility.setUpQuizDTO();
        QuizEntity quizEntity = QuizTestSetUpUtility.setUpQuizEntity();

        Mockito.when(objectMapper.convertValue(Mockito.any(QuizEntity.class), Mockito.eq(QuizDTO.class))).thenReturn(quizDTO);
        Mockito.when(objectMapper.convertValue(Mockito.any(QuizDTO.class), Mockito.eq(QuizEntity.class))).thenReturn(quizEntity);
        Mockito.when(quizRepository.save(Mockito.any())).thenReturn(quizDTO);
        quizService.updateQuiz(quizDTO);
    }

}