package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.Flashcard;
import com.jpdictionary.demo.repository.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class FlashcardService {

    @Autowired
    private FlashcardRepository flashcardRepository;

    private final String API_URL = "https://api.example.com/flashcards"; // Thay bằng API thật

    public List<Flashcard> getFlashcardsFromAPI() {
        RestTemplate restTemplate = new RestTemplate();
        Flashcard[] flashcards = restTemplate.getForObject(API_URL, Flashcard[].class);
        
        if (flashcards != null) {
            flashcardRepository.saveAll(List.of(flashcards));  // Lưu vào database
        }
        
        return flashcardRepository.findAll();
    }
}
