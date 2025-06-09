package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.Word;
import com.jpdictionary.demo.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public Optional<Word> getWordByName(String word) {
        return wordRepository.findByWord(word);
    }

    public List<Word> searchWords(String query) {
        return wordRepository.searchWordsNative(query);
    }

    public List<Word> getWordsByJlptLevel(String jlptLevel) {
        return wordRepository.findWordsByJlptLevel(jlptLevel);
    }

    public Word saveWord(Word word) {
        word.setCreatedAt(new Date());
        return wordRepository.save(word);
    }

    public Word updateWord(Long id, Word updatedWord) {
        Word existing = wordRepository.findById(id).orElseThrow();
        existing.setWord(updatedWord.getWord());
        existing.setReading(updatedWord.getReading());
        existing.setMeaning(updatedWord.getMeaning());
        existing.setPartOfSpeech(updatedWord.getPartOfSpeech());
        existing.setJlpt(updatedWord.getJlpt());
        existing.setIs_common(updatedWord.getIs_common());
        return wordRepository.save(existing);
    }

    public void deleteWord(Long id) {
        wordRepository.deleteById(id);
    }

    public List<Word> getWordsByIds(List<Long> ids) {
        return wordRepository.findByIds(ids);
    }
}
