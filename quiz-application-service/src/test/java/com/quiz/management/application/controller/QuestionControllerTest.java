package com.quiz.management.application.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.management.application.dto.QuestionDTO;
import com.quiz.management.application.dto.QuizDTO;
import com.quiz.management.application.entity.QuestionEntity;
import com.quiz.management.application.entity.QuizEntity;
import com.quiz.management.application.handler.QuestionException;
import com.quiz.management.application.service.QuestionService;
import com.quiz.management.application.utility.QuestionTestSetUpUtility;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    QuestionService questionService;

    @Test
    void test_createQuestion()  throws Exception{
        QuestionEntity question = QuestionTestSetUpUtility.setUpQuestionEntity();
        QuizEntity quiz = QuestionTestSetUpUtility.setUpQuizEntity();
        QuestionDTO questionDTO = QuestionTestSetUpUtility.setUpQuestionDTO();

        Mockito.when(questionService.createQuestion(questionDTO)).thenReturn(questionDTO);
        mockMvc.perform(MockMvcRequestBuilders.
                post("/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(questionDTO))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(questionService, Mockito.times(1)).createQuestion(Mockito.any(QuestionDTO.class));
    }

    @Test
    public void test_getAllQuestions() throws Exception {
        QuestionEntity question = QuestionTestSetUpUtility.setUpQuestionEntity();
        QuizEntity quiz = QuestionTestSetUpUtility.setUpQuizEntity();
        QuestionDTO questionDTO = QuestionTestSetUpUtility.setUpQuestionDTO();

        Mockito.when(questionService.getAllQuestions()).thenReturn(List.of(questionDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/questions").
                contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(questionService, Mockito.times(1)).getAllQuestions();
    }

    @Test
    void test_getQuestionById() throws Exception {
        QuestionEntity question = QuestionTestSetUpUtility.setUpQuestionEntity();
        QuizEntity quiz = QuestionTestSetUpUtility.setUpQuizEntity();
        QuestionDTO questionDTO = QuestionTestSetUpUtility.setUpQuestionDTO();

        Mockito.when(questionService.getQuestionById(Mockito.anyInt())).thenReturn(questionDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/questions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(questionService, Mockito.times(1)).getQuestionById(Mockito.anyInt());
    }

    @Test
    void test_deleteQuestionById() throws Exception {
        QuestionEntity question = QuestionTestSetUpUtility.setUpQuestionEntity();
        QuizEntity quiz = QuestionTestSetUpUtility.setUpQuizEntity();
        QuestionDTO questionDTO = QuestionTestSetUpUtility.setUpQuestionDTO();

        int quesId = 1;
        mockMvc.perform(MockMvcRequestBuilders.delete("/questions/{quesId}", quesId).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isNoContent());
        Mockito.verify(questionService, Mockito.times(1)).deleteQuestionById(Mockito.anyInt());
    }

    @Test
    public void update_question() throws Exception {
        QuestionDTO questionDTO = QuestionTestSetUpUtility.setUpQuestionDTO();

        Mockito.when(questionService.updateQuestionById(Mockito.anyInt(), Mockito.any())).thenReturn(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.put("/questions/1").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(questionDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("Science"));

        Mockito.verify(questionService, Mockito.times(1)).updateQuestionById(Mockito.anyInt(), Mockito.any());
    }
}