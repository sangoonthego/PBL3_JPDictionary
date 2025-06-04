package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.SavedWord;
import com.jpdictionary.demo.service.SavedWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saved-words")
public class SavedWordController {

    @Autowired
    private SavedWordService savedWordService;

    // Lấy danh sách từ đã lưu của user
    @GetMapping("/user/{userId}")
    public List<SavedWord> getSavedWords(@PathVariable Long userId) {
        return savedWordService.getSavedWordsByUser(userId);
    }

    // Lưu từ cho user
    @PostMapping("/user/{userId}/word/{wordId}")
    public SavedWord saveWord(@PathVariable Long userId, @PathVariable Long wordId) {
        return savedWordService.saveWord(userId, wordId);
    }

    // Xóa từ đã lưu của user
    @DeleteMapping("/user/{userId}/word/{wordId}")
    public void deleteSavedWord(@PathVariable Long userId, @PathVariable Long wordId) {
        savedWordService.deleteSavedWord(userId, wordId);
    }
}
