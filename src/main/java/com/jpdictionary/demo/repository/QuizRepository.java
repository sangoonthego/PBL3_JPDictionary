package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByUserId(Long userId);  // Lấy danh sách quiz theo người dùng
}
