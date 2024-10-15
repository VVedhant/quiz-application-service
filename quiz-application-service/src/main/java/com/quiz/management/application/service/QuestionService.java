package com.quiz.management.application.service;

import com.quiz.management.application.dto.QuestionDTO;
import com.quiz.management.application.entity.QuestionEntity;
import com.quiz.management.application.handler.QuestionException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;

import java.util.List;

public interface QuestionService {

QuestionDTO createQuestion(QuestionDTO questionDTO) throws QuestionException;

    void deleteQuestionById( Integer questionId);
    QuestionDTO updateQuestionById(Integer id, QuestionDTO updatedQuestionDTO) throws QuestionException;
    QuestionDTO getQuestionById(Integer id) throws QuestionException;
    List<QuestionDTO> getAllQuestions();
}
