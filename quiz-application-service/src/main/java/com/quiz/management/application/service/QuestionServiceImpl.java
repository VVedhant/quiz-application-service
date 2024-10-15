package com.quiz.management.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.management.application.dto.QuestionDTO;
import com.quiz.management.application.entity.QuestionEntity;
import com.quiz.management.application.entity.QuizEntity;
import com.quiz.management.application.handler.QuestionException;
import com.quiz.management.application.repository.QuestionRepository;
import com.quiz.management.application.repository.QuizRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizService quizService;

    private QuizEntity quizEntity;


    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) throws QuestionException {
//        QuizEntity quiz = Optional.ofNullable(quizRepository.findByCategory(questionDTO.getCategory()))
//                .orElseThrow(() -> new QuestionException("Quiz category not found"));


//        QuestionEntity questionEntity =
//                objectMapper.convertValue(questionDTO, QuestionEntity.class);
//        questionEntity.setQuiz(quiz);
//        QuestionEntity savedQuestionEntity = questionRepository.save(questionEntity);
//        return objectMapper.convertValue(savedQuestionEntity, QuestionDTO.class);

        QuizEntity quiz = quizRepository.findByCategory(questionDTO.getCategory())
                .orElseThrow(() -> new QuestionException("Category not found"));

        QuestionEntity question = objectMapper.convertValue(questionDTO, QuestionEntity.class);
        question.setQuiz(quiz);
        QuestionEntity savedQuestionEntity = questionRepository.save(question);
        return objectMapper.convertValue(savedQuestionEntity, QuestionDTO.class);
    }

    @Override
    public void deleteQuestionById(Integer questionId){
        questionRepository.deleteById(questionId);
    }

    @Override
    public QuestionDTO updateQuestionById(Integer id, QuestionDTO updatedQuestionDTO) throws QuestionException {
        QuestionEntity questionEntity = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionException("Question not found"));
        QuestionEntity question = objectMapper.convertValue(updatedQuestionDTO, QuestionEntity.class);
        question.setQuiz(questionEntity.getQuiz());
        QuestionEntity updatedQuestionEntity = questionRepository.save(question);
        return objectMapper.convertValue(updatedQuestionEntity, QuestionDTO.class);
    }

    @Override
    public QuestionDTO getQuestionById(Integer id) throws QuestionException {
        QuestionEntity questionEntity = questionRepository.findById(id)
                .orElseThrow(() ->
                        new QuestionException("Question for question id " + id + " not found"));
        return objectMapper.convertValue(questionEntity, QuestionDTO.class);
    }

    @Override
    public List<QuestionDTO> getAllQuestions() {
        Iterable<QuestionEntity> questionEntities = questionRepository.findAll();
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        questionEntities.forEach(questionEntity -> questionDTOs.add(objectMapper.convertValue(questionEntity, QuestionDTO.class)));
        return questionDTOs;
    }
}