package com.quiz.management.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.management.application.dto.QuizDTO;
import com.quiz.management.application.entity.QuestionEntity;
import com.quiz.management.application.entity.QuizEntity;
import com.quiz.management.application.handler.QuizException;
import com.quiz.management.application.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    @Autowired
    private final QuizRepository quizRepository;

    @Autowired
    private final ObjectMapper objectMapper;


    public QuizDTO createQuiz(QuizDTO quizDTO) {
        QuizEntity quiz = this.quizRepository.findByCategory(quizDTO.getCategory())
                .orElse(objectMapper.convertValue(quizDTO, QuizEntity.class));
        List<QuestionEntity> questionEntityList = quiz.getQuestions();
        questionEntityList.forEach(question -> question.setQuiz(quiz));
        quizRepository.save(quiz);
        QuizDTO resQuizDTO = objectMapper.convertValue(quiz, QuizDTO.class);
        return objectMapper.convertValue(resQuizDTO, QuizDTO.class);
    }

    @Override
    public void deleteQuizById(Integer quizId) {
        quizRepository.deleteById(quizId);
    }

//    public void deleteQuestionBycategory(String category, Integer questionId) {
//        Optional<QuizEntity> quiz = quizRepository.findByCategory(category);
//        if (!quiz.get().getQuestions().isEmpty()) {
//            List<QuestionEntity> questions = quiz.get().getQuestions();
//            questions.removeIf((question) -> Objects.equals(question.getId(), questionId));
//            quiz.setQuestions(questions);
//            Optional<QuizEntity> quizEntity = quiz;
//            quizRepository.save(quizEntity);
//        }
//    }

    @Override
    public QuizDTO getQuizById(Integer id) throws QuizException {
        QuizEntity quizEntity = quizRepository.findById(id)
                .orElseThrow(() -> new QuizException("Quiz not found"));
        return objectMapper.convertValue(quizEntity, QuizDTO.class);
    }

    @Override
    public List<QuizDTO> getAllQuizzes() {
        List<QuizEntity> quizEntities = (List<QuizEntity>) quizRepository.findAll();
        return quizEntities.stream()
                .map(quizEntity -> objectMapper.convertValue(quizEntity, QuizDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public void delete() {
        quizRepository.deleteAll();
    }


    @Override
    public QuizDTO updateQuiz(QuizDTO quizDTO) {
        QuizEntity quiz = objectMapper.convertValue(quizDTO, QuizEntity.class);
        quiz.getQuestions()
                .forEach(question -> {
                    question.setQuiz(quiz);
                });
        quizRepository.save(quiz);
        return objectMapper.convertValue(quiz, QuizDTO.class);
    }


//    @Override
//    public QuizDTO updateQuiz(QuizDTO quizDTO) {
//        QuizEntity quizEntity = objectMapper.convertValue(quizDTO, QuizEntity.class);
//        quizEntity.getQuestions().forEach(questionEntity -> questionEntity.setQuiz(quizEntity));
//        QuizEntity quiz = quizRepository.save(quizEntity);
//        return objectMapper.convertValue(quiz, QuizDTO.class);
//    }

}