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

    // Fetch từ API ngoài
    @GetMapping("/fetch")
    public List<Flashcard> fetchFlashcards() {
        return flashcardService.getFlashcardsFromAPI();
    }

    // CREATE
    @PostMapping
    public Flashcard createFlashcard(@RequestBody Flashcard flashcard) {
        return flashcardService.saveFlashcard(flashcard);
    }

    // READ ALL
    @GetMapping
    public List<Flashcard> getAllFlashcards() {
        return flashcardService.getAllFlashcards();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Flashcard getFlashcardById(@PathVariable Long id) {
        return flashcardService.getFlashcardById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Flashcard updateFlashcard(@PathVariable Long id, @RequestBody Flashcard flashcard) {
        return flashcardService.updateFlashcard(id, flashcard);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteFlashcard(@PathVariable Long id) {
        flashcardService.deleteFlashcard(id);
    }
}

