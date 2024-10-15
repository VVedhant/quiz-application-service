package com.quiz.management.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quiz")
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private int  id;

    @Column(name = "category",unique = true)
    private String category;

    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuestionEntity> questions;
}