package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.FlashcardWord;
import com.jpdictionary.demo.service.FlashcardWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcard-words")
public class FlashcardWordController {

    @Autowired
    private FlashcardWordService flashcardWordService;

    @GetMapping("/fetch")
    public List<FlashcardWord> fetchFlashcardWords() {
        return flashcardWordService.getFlashcardWordsFromAPI();
    }
}
