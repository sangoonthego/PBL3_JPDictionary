package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.WordKanji;
import com.jpdictionary.demo.service.WordKanjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/word-kanji")
public class WordKanjiController {

    @Autowired
    private WordKanjiService wordKanjiService;

    @GetMapping
    public List<WordKanji> getAllWordKanji() {
        return wordKanjiService.getAllWordKanji();
    }

    @GetMapping("/{id}")
    public Optional<WordKanji> getWordKanjiById(@PathVariable Long id) {
        return wordKanjiService.getWordKanjiById(id);
    }

    @PostMapping("/add")
    public WordKanji createWordKanji(@RequestParam Long wordId, @RequestParam Long kanjiId) {
        return wordKanjiService.createWordKanji(wordId, kanjiId);
    }

    @DeleteMapping("/{id}")
    public void deleteWordKanji(@PathVariable Long id) {
        wordKanjiService.deleteWordKanji(id);
    }
}
