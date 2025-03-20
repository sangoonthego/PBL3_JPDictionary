package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.Quiz;
import com.jpdictionary.demo.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    private final String API_URL = "https://api.example.com/quizzes"; // Thay bằng API thật

    public List<Quiz> getQuizzesFromAPI() {
        RestTemplate restTemplate = new RestTemplate();
        Quiz[] quizList = restTemplate.getForObject(API_URL, Quiz[].class);
        
        if (quizList != null) {
            quizRepository.saveAll(List.of(quizList));  // Lưu vào database
        }
        
        return quizRepository.findAll();
    }

    public void saveQuizResult(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public List<Quiz> getUserQuizzes(Long userId) {
        return quizRepository.findByUserId(userId);
    }
}
