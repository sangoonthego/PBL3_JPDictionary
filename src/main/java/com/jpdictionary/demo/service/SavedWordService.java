package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.SavedWord;
import com.jpdictionary.demo.models.SavedWordId;
import com.jpdictionary.demo.repository.SavedWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavedWordService {

    @Autowired
    private SavedWordRepository savedWordRepository;

    // Lấy danh sách từ đã lưu của 1 user
    public List<SavedWord> getSavedWordsByUser(Long userId) {
        return savedWordRepository.findByIdUserId(userId);
    }

    // Lưu 1 từ cho user
    public SavedWord saveWord(Long userId, Long wordId) {
        SavedWordId id = new SavedWordId(userId, wordId);

        Optional<SavedWord> existing = savedWordRepository.findById(id);
        if (existing.isPresent()) {
            return existing.get();
        }

        SavedWord savedWord = new SavedWord();
        savedWord.setId(id);  // set id composite key
        return savedWordRepository.save(savedWord);
    }

    // Xóa 1 từ đã lưu
    public void deleteSavedWord(Long userId, Long wordId) {
        SavedWordId id = new SavedWordId(userId, wordId);
        savedWordRepository.deleteById(id);
    }
}
