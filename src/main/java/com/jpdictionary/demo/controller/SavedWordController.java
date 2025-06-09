package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.SavedWord;
import com.jpdictionary.demo.service.SavedWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saved-words")
public class SavedWordController {

    @Autowired
    private SavedWordService savedWordService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SavedWord>> getSavedWords(@PathVariable Long userId) {
        List<SavedWord> savedWords = savedWordService.getSavedWordsByUser(userId);
        if (savedWords.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(savedWords);
    }

    @PostMapping("/user/{userId}/word/{wordId}")
    public ResponseEntity<?> saveWord(@PathVariable Long userId, @PathVariable Long wordId) {
        try {
            SavedWord savedWord = savedWordService.saveWord(userId, wordId);
            return ResponseEntity.ok(savedWord);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/user/{userId}/word/{wordId}")
    public ResponseEntity<Void> deleteSavedWord(@PathVariable Long userId, @PathVariable Long wordId) {
        try {
            savedWordService.deleteSavedWord(userId, wordId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
