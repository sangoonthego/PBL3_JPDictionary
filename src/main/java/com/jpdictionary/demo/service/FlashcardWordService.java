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
    
    public FlashcardWord saveFlashcardWord(FlashcardWord flashcardWord) {
        return flashcardWordRepository.save(flashcardWord);
    }

    public List<FlashcardWord> getAllFlashcardWords() {
        return flashcardWordRepository.findAll();
    }

    public FlashcardWord getFlashcardWordById(Long id) {
        return flashcardWordRepository.findById(id).orElse(null);
    }

    public FlashcardWord updateFlashcardWord(Long id, FlashcardWord flashcardWord) {
        FlashcardWord existing = flashcardWordRepository.findById(id).orElse(null);
        if (existing != null) {
            flashcardWord.setId(id);
            return flashcardWordRepository.save(flashcardWord);
        }
        return null;
    }

    public void deleteFlashcardWord(Long id) {
        flashcardWordRepository.deleteById(id);
    }
}
