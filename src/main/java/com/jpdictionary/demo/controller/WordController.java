package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.Word;
import com.jpdictionary.demo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/words")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping
    public List<Word> getAllWords() {
        return wordService.getAllWords();
    }

    @GetMapping("/{word}")
    public Optional<Word> getWord(@PathVariable String word) {
        return wordService.getWordByName(word);
    }

    @PostMapping("/fetch/{word}")
    public Word fetchWord(@PathVariable String word) {
        return wordService.fetchWordFromAPI(word);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Word> updateWord(@PathVariable Long id, @RequestBody Word updatedWord) {
        Optional<Word> result = wordService.updateWord(id, updatedWord);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable Long id) {
        if (wordService.deleteWord(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Word createWord(@RequestBody Word word) {
        return wordService.saveWord(word);
    }
}
