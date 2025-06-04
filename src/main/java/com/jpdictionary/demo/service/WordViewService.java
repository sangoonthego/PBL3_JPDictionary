package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.WordView;
import com.jpdictionary.demo.repository.WordViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordViewService {

    @Autowired
    private WordViewRepository wordViewRepository;

    public WordView getWordView(Long wordId) {
        return wordViewRepository.findById(wordId)
                .orElseGet(() -> {
                    WordView newView = new WordView();
                    newView.setWordId(wordId);
                    newView.setViewCount(0);
                    return wordViewRepository.save(newView);
                });
    }

    public WordView incrementViewCount(Long wordId) {
        WordView wordView = getWordView(wordId);
        wordView.setViewCount(wordView.getViewCount() + 1);
        return wordViewRepository.save(wordView);
    }
}
