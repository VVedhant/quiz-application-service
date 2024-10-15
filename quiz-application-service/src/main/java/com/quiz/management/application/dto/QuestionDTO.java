package com.quiz.management.application.dto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;

    @NotBlank(message = "Category cannot be null")
    private String category;

    @NotBlank(message = "Description is a mandatory field")
    private String description;

    @NotEmpty(message = "Options are mandatory")
    private List<String> options;

    @Positive(message = "Correct option cannot be negative or zero")
    private Integer correctOption;

    @NotBlank(message = "Difficulty is a mandatory field")
    private String difficulty;

}