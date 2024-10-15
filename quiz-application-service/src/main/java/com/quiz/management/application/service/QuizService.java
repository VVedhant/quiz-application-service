package com.quiz.management.application.service;

import com.quiz.management.application.dto.QuizDTO;
import com.quiz.management.application.handler.QuizException;

import java.util.List;

public interface QuizService {

    QuizDTO createQuiz(QuizDTO quizDTO);
    //public void deleteQuestionByCategory(String category,Integer quizId);
    void deleteQuizById(Integer quizId);
    QuizDTO getQuizById(Integer id)  throws QuizException;
    List<QuizDTO> getAllQuizzes();
    void delete();
//    QuizEntity  getQuizByCategory(String category, Integer questionId);
    QuizDTO updateQuiz(QuizDTO quizDTO);

}