package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.WordView;
import com.jpdictionary.demo.repository.WordViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordViewService {

    @Autowired
    private WordViewRepository wordViewRepository;

    public WordView getWordView(Long wordId) {
        return wordViewRepository.findById(wordId)
                .orElse(null);
    }

    public WordView incrementViewCount(Long wordId) {
        wordViewRepository.upsertAndIncrementView(wordId);
        return getWordView(wordId); // Return the latest record after update
    }

    public List<WordView> getAllWordViewsSorted() {
        return wordViewRepository.findAllByViewCount();
    }

    public List<WordView> getTop12WordViews() {
        return wordViewRepository.findTop18ByViewCount();
    }
}
