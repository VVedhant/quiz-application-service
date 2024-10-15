package com.quiz.management.application.repository;

import com.quiz.management.application.dto.QuestionDTO;
import com.quiz.management.application.entity.QuestionEntity;
import com.quiz.management.application.entity.QuizEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface QuestionRepository extends CrudRepository<QuestionEntity, Integer> {
    Optional<QuestionEntity> findByCategory(String category);
}