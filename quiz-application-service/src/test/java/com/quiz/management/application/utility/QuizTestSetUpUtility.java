package com.quiz.management.application.utility;

import com.quiz.management.application.dto.QuestionDTO;
import com.quiz.management.application.dto.QuizDTO;
import com.quiz.management.application.entity.QuestionEntity;
import com.quiz.management.application.entity.QuizEntity;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class QuizTestSetUpUtility {

    public QuizEntity setUpQuizEntity()
    {
        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setCategory("Science");
        QuestionEntity question1 = new QuestionEntity();
        question1.setCategory("Science");
        question1.setDescription("Which of these elements is most common in the air we breathe?");
        question1.setOptions(Arrays.asList("Hydrogen", "Nitrogen", "Silicon", "Carbon"));
        question1.setCorrectOption(1);
        question1.setDifficulty("Easy");
        question1.setQuiz(quizEntity);
        quizEntity.setQuestions(List.of(question1));

        return quizEntity;
    }

    public QuizDTO setUpQuizDTO(){
        QuizDTO quizDTO = QuizDTO.builder()
                .category("Science")
                .build();

        quizDTO.setQuestions(List.of(QuestionDTO.builder()
                .category("Science")
                .options(List.of("1", "2", "3", "4"))
                .description("Test question")
                .difficulty("Easy")
                .correctOption(3)
                .build()));

        return quizDTO;
    }
}
