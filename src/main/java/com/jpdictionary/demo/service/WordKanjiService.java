package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.WordKanji;
import com.jpdictionary.demo.models.Word;
import com.jpdictionary.demo.models.Kanji;
import com.jpdictionary.demo.repository.WordKanjiRepository;
import com.jpdictionary.demo.repository.WordRepository;
import com.jpdictionary.demo.repository.KanjiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WordKanjiService {

    @Autowired
    private WordKanjiRepository wordKanjiRepository;

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private KanjiRepository kanjiRepository;

    public List<WordKanji> getAllWordKanji() {
        return wordKanjiRepository.findAll();
    }

    public Optional<WordKanji> getWordKanjiById(Long id) {
        return wordKanjiRepository.findById(id);
    }

    public WordKanji createWordKanji(Long wordId, Long kanjiId) {
        Optional<Word> word = wordRepository.findById(wordId);
        Optional<Kanji> kanji = kanjiRepository.findById(kanjiId);

        if (word.isPresent() && kanji.isPresent()) {
            WordKanji wordKanji = new WordKanji();
            wordKanji.setWord(word.get());
            wordKanji.setKanji(kanji.get());
            return wordKanjiRepository.save(wordKanji);
        }
        return null;
    }

    public void deleteWordKanji(Long id) {
        wordKanjiRepository.deleteById(id);
    }
}
