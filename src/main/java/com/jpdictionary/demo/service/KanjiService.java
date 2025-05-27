package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.Kanji;
import com.jpdictionary.demo.repository.KanjiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class KanjiService {

    @Autowired
    private KanjiRepository kanjiRepository;

    private final String API_URL = "https://api.example.com/kanji"; // Thay bằng API thật

    public List<Kanji> getKanjiFromAPI() {
        RestTemplate restTemplate = new RestTemplate();
        Kanji[] kanjiList = restTemplate.getForObject(API_URL, Kanji[].class);
        
        if (kanjiList != null) {
            kanjiRepository.saveAll(List.of(kanjiList));  // Lưu vào database
        }
        
        return kanjiRepository.findAll();
    }
    
    public List<Kanji> getAllKanji() {
        return kanjiRepository.findAll();
    }

    public Kanji getKanjiById(Long id) {
        return kanjiRepository.findById(id).orElse(null);
    }

    public Kanji createKanji(Kanji kanji) {
        return kanjiRepository.save(kanji);
    }

    public Kanji updateKanji(Long id, Kanji kanji) {
        Kanji existing = getKanjiById(id);
        if (existing != null) {
            kanji.setId(id);
            return kanjiRepository.save(kanji);
        }
        return null;
    }

    public void deleteKanji(Long id) {
        kanjiRepository.deleteById(id);
    }
}
