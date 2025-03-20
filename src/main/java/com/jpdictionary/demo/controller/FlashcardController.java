package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.Flashcard;
import com.jpdictionary.demo.service.FlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcards")
public class FlashcardController {

    @Autowired
    private FlashcardService flashcardService;

    @GetMapping("/fetch")
    public List<Flashcard> fetchFlashcards() {
        return flashcardService.getFlashcardsFromAPI();
    }
}
