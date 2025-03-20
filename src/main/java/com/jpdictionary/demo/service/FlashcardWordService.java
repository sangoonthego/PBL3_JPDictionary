package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.FlashcardWord;
import com.jpdictionary.demo.repository.FlashcardWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class FlashcardWordService {

    @Autowired
    private FlashcardWordRepository flashcardWordRepository;

    private final String API_URL = "https://api.example.com/flashcardWords"; // Thay bằng API thật

    public List<FlashcardWord> getFlashcardWordsFromAPI() {
        RestTemplate restTemplate = new RestTemplate();
        FlashcardWord[] flashcardWords = restTemplate.getForObject(API_URL, FlashcardWord[].class);
        
        if (flashcardWords != null) {
            flashcardWordRepository.saveAll(List.of(flashcardWords));  // Lưu vào database
        }
        
        return flashcardWordRepository.findAll();
    }
}
