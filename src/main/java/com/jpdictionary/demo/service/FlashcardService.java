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
    
    public Flashcard saveFlashcard(Flashcard flashcard) {
        return flashcardRepository.save(flashcard);
    }

    public List<Flashcard> getAllFlashcards() {
        return flashcardRepository.findAll();
    }

    public Flashcard getFlashcardById(Long id) {
        return flashcardRepository.findById(id).orElse(null);
    }

    public Flashcard updateFlashcard(Long id, Flashcard flashcard) {
        Flashcard existing = flashcardRepository.findById(id).orElse(null);
        if (existing != null) {
            flashcard.setId(id);
            return flashcardRepository.save(flashcard);
        }
        return null;
    }

    public void deleteFlashcard(Long id) {
        flashcardRepository.deleteById(id);
    }
}
