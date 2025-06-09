package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.Kanji;
import com.jpdictionary.demo.repository.KanjiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class KanjiService {

    @Autowired
    private KanjiRepository kanjiRepository;

    public List<Kanji> getKanjiByLevel(int jlptLevel) {
        return kanjiRepository.findKanjiByJlptLevel(jlptLevel);
    }

    public Kanji saveKanji(Kanji kanji) {
        kanji.setCreatedAt(new Date());
        return kanjiRepository.save(kanji);
    }

    public Kanji updateKanji(Long id, Kanji updatedKanji) {
        Kanji existing = kanjiRepository.findById(id).orElseThrow();
        existing.setKanji(updatedKanji.getKanji());
        existing.setOnyomi(updatedKanji.getOnyomi());
        existing.setKunyomi(updatedKanji.getKunyomi());
        existing.setMeaning(updatedKanji.getMeaning());
        existing.setStrokes(updatedKanji.getStrokes());
        return kanjiRepository.save(existing);
    }

    public void deleteKanji(Long id) {
        kanjiRepository.deleteById(id);
    }
}
