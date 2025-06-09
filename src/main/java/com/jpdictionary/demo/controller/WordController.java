package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.Word;
import com.jpdictionary.demo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/words")
@CrossOrigin(origins = "http://localhost:3000")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping
    public ResponseEntity<?> getAllWords(@RequestParam(value = "q", required = false) String query) {
        try {
            List<Word> words;
            if (query != null && !query.isEmpty()) {
                words = wordService.searchWords(query);
            } else {
                words = wordService.getAllWords();
            }
            return ResponseEntity.ok(words);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @GetMapping("/{word}")
    public ResponseEntity<?> getWord(@PathVariable String word) {
        Optional<Word> optionalWord = wordService.getWordByName(word);
        if (optionalWord.isPresent()) {
            return ResponseEntity.ok(optionalWord.get());
        } else {
            return ResponseEntity.status(404).body(Collections.singletonMap("message", "Word not found"));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchWords(@RequestParam String query) {
        try {
            List<Word> words = wordService.searchWords(query);
            return ResponseEntity.ok(words);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @GetMapping("/jlpt")
    public ResponseEntity<?> getWordsByJlptLevel(@RequestParam(name = "jlpt_level") String jlptLevel) {
        try {
            List<Word> words = wordService.getWordsByJlptLevel(jlptLevel);
            return ResponseEntity.ok(words);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createWord(@RequestBody Word word) {
        try {
            Word saved = wordService.saveWord(word);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWord(@PathVariable Long id, @RequestBody Word updatedWord) {
        try {
            Word updated = wordService.updateWord(id, updatedWord);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWord(@PathVariable Long id) {
        try {
            wordService.deleteWord(id);
            return ResponseEntity.ok(Collections.singletonMap("message", "Word deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @PostMapping("/by-ids")
    public ResponseEntity<?> getWordsByIds(@RequestBody List<Long> ids) {
        try {
            List<Word> words = wordService.getWordsByIds(ids);
            return ResponseEntity.ok(words);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }
}
