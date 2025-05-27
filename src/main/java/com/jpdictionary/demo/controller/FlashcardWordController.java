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

    // Fetch từ API ngoài
    @GetMapping("/fetch")
    public List<FlashcardWord> fetchFlashcardWords() {
        return flashcardWordService.getFlashcardWordsFromAPI();
    }

    // CREATE
    @PostMapping
    public FlashcardWord createFlashcardWord(@RequestBody FlashcardWord flashcardWord) {
        return flashcardWordService.saveFlashcardWord(flashcardWord);
    }

    // READ ALL
    @GetMapping
    public List<FlashcardWord> getAllFlashcardWords() {
        return flashcardWordService.getAllFlashcardWords();
    }

    // READ ONE
    @GetMapping("/{id}")
    public FlashcardWord getFlashcardWordById(@PathVariable Long id) {
        return flashcardWordService.getFlashcardWordById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public FlashcardWord updateFlashcardWord(@PathVariable Long id, @RequestBody FlashcardWord flashcardWord) {
        return flashcardWordService.updateFlashcardWord(id, flashcardWord);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteFlashcardWord(@PathVariable Long id) {
        flashcardWordService.deleteFlashcardWord(id);
    }
}

