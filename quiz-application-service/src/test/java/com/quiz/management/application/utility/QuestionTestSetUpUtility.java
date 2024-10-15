package com.quiz.management.application.utility;

import com.quiz.management.application.dto.QuestionDTO;
import com.quiz.management.application.entity.QuestionEntity;
import com.quiz.management.application.entity.QuizEntity;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class QuestionTestSetUpUtility {

    public QuestionEntity setUpQuestionEntity() {
        QuizEntity quiz = QuizEntity.builder()
                .category("Science")
                .build();

        QuestionEntity question = QuestionEntity.builder()
                .id(1)
                .category("Science")
                .description("Which of these elements is most common in the air we breathe?")
                .options(Arrays.asList("Hydrogen", "Nitrogen", "Silicon", "Carbon"))
                .correctOption(1)
                .difficulty("easy")
                .quiz(quiz)
                .build();
        return question;
    }

    public QuizEntity setUpQuizEntity() {
        QuizEntity quiz = QuizEntity.builder()
                .category("Science")
                .build();

        QuestionEntity question = setUpQuestionEntity();
        quiz.setQuestions(List.of(question));
        return quiz;
    }

    public QuestionDTO setUpQuestionDTO(){
        QuestionDTO questionDTO = QuestionDTO.builder()
                .id(1)
                .category("Science")
                .options(Arrays.asList("1", "2", "3", "4"))
                .description("Test question")
                .difficulty("Easy")
                .correctOption(3)
                .build();

//        QuestionDTO questionDTO2 = QuestionDTO.builder()
//                .id(2)
//                .category("Math")
//                .options(Arrays.asList("5", "6", "7", "8"))
//                .description("Test question 2")
//                .difficulty("Medium")
//                .correctOption(1)
//                .build();

        return questionDTO;
    }
}