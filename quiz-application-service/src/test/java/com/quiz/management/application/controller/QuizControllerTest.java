package com.quiz.management.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.management.application.dto.QuestionDTO;
import com.quiz.management.application.dto.QuizDTO;
import com.quiz.management.application.entity.QuizEntity;
import com.quiz.management.application.service.QuizService;
import com.quiz.management.application.utility.QuizTestSetUpUtility;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class QuizControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired //Mock
    ObjectMapper objectMapper;

    @MockBean //InjectMock
    QuizService quizService;

    @Test
    public void test_createQuiz() throws Exception {

        QuizDTO quizDTO = QuizTestSetUpUtility.setUpQuizDTO();

        Mockito.when(quizService.createQuiz(Mockito.any())).thenReturn(Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders
                .post("/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(quizDTO))
        ).andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(quizService, Mockito.times(1)).createQuiz(Mockito.any());
    }

    @Test
    void test_getAllQuizzes() throws Exception {
//        List<QuizDTO> quizDTOList = List.of(
//                QuizDTO.builder()
//                        .category("Science")
//                        .questions(List.of(
//                                QuestionDTO.builder()
//                                        .category("Science")
//                                        .options(List.of("1", "2", "3", "4"))
//                                        .description("Test question 1")
//                                        .difficulty("Easy")
//                                        .correctOption(3)
//                                        .build()
//                        ))
//                        .build(),
//                QuizDTO.builder()
//                        .category("Math")
//                        .questions(List.of(
//                                QuestionDTO.builder()
//                                        .category("Math")
//                                        .options(List.of("A", "B", "C", "D"))
//                                        .description("Test question 2")
//                                        .difficulty("Medium")
//                                        .correctOption(1)
//                                        .build()
//                        ))
//                        .build()
//        );

        QuizDTO quizDTO = QuizTestSetUpUtility.setUpQuizDTO();
        QuizEntity quizEntity = QuizTestSetUpUtility.setUpQuizEntity();

        Mockito.when(quizService.getAllQuizzes()).thenReturn(List.of(quizDTO));
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/quizzes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(quizService, Mockito.times(1)).getAllQuizzes();
    }


    @Test
    public void test_getQuizById() throws Exception {
        int qid=1;
        QuizDTO quizDTO = QuizTestSetUpUtility.setUpQuizDTO();
        Mockito.when(quizService.getQuizById(qid)).thenReturn(quizDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/quizzes/{id}", qid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(quizService,Mockito.times(1)).getQuizById(qid);
    }


    @Test
    void test_deleteQuiz() throws Exception {
        int qid=1;
        Mockito.doNothing().when(quizService).deleteQuizById(qid);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/quizzes/{qid}",qid))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        Mockito.verify(quizService, Mockito.times(1)).deleteQuizById(qid);
    }

    @Test
    void test_getDeleteAll()  throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/quizzes"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        Mockito.verify(quizService, Mockito.times(1)).delete();
    }

    @Test
    public void test_updateQuiz() throws Exception {

        QuizDTO updatedQuizDTO = QuizDTO.builder()
                .category("Science")
                .questions(List.of(
                        QuestionDTO.builder()
                                .category("Science")
                                .options(List.of("1", "2", "3", "4"))
                                .description("Updated question")
                                .difficulty("Medium")
                                .correctOption(2)
                                .build()))
                .build();

        Mockito.when(quizService.updateQuiz(Mockito.any(QuizDTO.class))).thenReturn(Mockito.any());
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/quizzes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Mockito.any())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(quizService, Mockito.times(1)).updateQuiz(Mockito.any(QuizDTO.class));
    }
}