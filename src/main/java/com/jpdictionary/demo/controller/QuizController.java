package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.Quiz;
import com.jpdictionary.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/fetch")
    public List<Quiz> fetchQuizzes() {
        return quizService.getQuizzesFromAPI();
    }

    @PostMapping("/submit")
    public String submitQuiz(@RequestBody Quiz quiz) {
        quizService.saveQuizResult(quiz);
        return "Quiz result saved successfully!";
    }

    @GetMapping("/user/{userId}")
    public List<Quiz> getUserQuizzes(@PathVariable Long userId) {
        return quizService.getUserQuizzes(userId);
    }
}
