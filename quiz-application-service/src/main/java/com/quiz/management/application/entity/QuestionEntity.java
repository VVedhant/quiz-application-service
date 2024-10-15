package com.quiz.management.application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int  id;

    @Column(name = "category")
    private String category;

    @Column(name = "description",unique = true)
    private String description;

    @ElementCollection
    @CollectionTable(name = "question_options")
    private List<String> options;

    @Column(name = "correct_option")
    private Integer correctOption;

    @Column(name = "difficulty")
    private String difficulty;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntity quiz;
}
