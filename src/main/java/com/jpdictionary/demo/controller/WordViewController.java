package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.WordView;
import com.jpdictionary.demo.service.WordViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/word-views")
public class WordViewController {

    @Autowired
    private WordViewService wordViewService;

    @GetMapping("/{wordId}")
    public ResponseEntity<?> getWordView(@PathVariable Long wordId) {
        WordView wordView = wordViewService.getWordView(wordId);
        if (wordView != null) {
            return ResponseEntity.ok(wordView);
        } else {
            return ResponseEntity.status(404).body(Collections.singletonMap("message", "WordView not found"));
        }
    }
    
    @PostMapping("/{wordId}/increment")
    public ResponseEntity<?> incrementViewCount(@PathVariable Long wordId) {
        try {
            WordView updated = wordViewService.incrementViewCount(wordId);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<WordView>> getAllWordViewsSorted() {
        List<WordView> list = wordViewService.getAllWordViewsSorted();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/top")
    public ResponseEntity<List<WordView>> getTop12WordViews() {
        List<WordView> topList = wordViewService.getTop12WordViews();
        return ResponseEntity.ok(topList);
    }
}
