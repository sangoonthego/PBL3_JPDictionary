package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.WordView;
import com.jpdictionary.demo.service.WordViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/word-views")
public class WordViewController {

    @Autowired
    private WordViewService wordViewService;

    // Lấy số view hiện tại của từ
    @GetMapping("/{wordId}")
    public WordView getWordView(@PathVariable Long wordId) {
        return wordViewService.getWordView(wordId);
    }

    // Tăng số lượt view của từ
    @PostMapping("/{wordId}/increment")
    public WordView incrementViewCount(@PathVariable Long wordId) {
        return wordViewService.incrementViewCount(wordId);
    }
}
