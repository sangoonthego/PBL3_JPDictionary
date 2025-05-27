package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.PartOfSpeech;
import com.jpdictionary.demo.repository.PartOfSpeechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PartOfSpeechService {

    @Autowired
    private PartOfSpeechRepository partOfSpeechRepository;

    private final String API_URL = "https://api.example.com/part-of-speech"; // Thay bằng API thật

    public List<PartOfSpeech> getPartsOfSpeechFromAPI() {
        RestTemplate restTemplate = new RestTemplate();
        PartOfSpeech[] partsOfSpeechList = restTemplate.getForObject(API_URL, PartOfSpeech[].class);
        
        if (partsOfSpeechList != null) {
            partOfSpeechRepository.saveAll(List.of(partsOfSpeechList));  // Lưu vào database
        }
        
        return partOfSpeechRepository.findAll();
    }
    
    public List<PartOfSpeech> getAllPartsOfSpeech() {
        return partOfSpeechRepository.findAll();
    }

    public PartOfSpeech getPartOfSpeechById(Long id) {
        return partOfSpeechRepository.findById(id).orElse(null);
    }

    public PartOfSpeech createPartOfSpeech(PartOfSpeech pos) {
        return partOfSpeechRepository.save(pos);
    }

    public PartOfSpeech updatePartOfSpeech(Long id, PartOfSpeech pos) {
        PartOfSpeech existing = getPartOfSpeechById(id);
        if (existing != null) {
            pos.setId(id);
            return partOfSpeechRepository.save(pos);
        }
        return null;
    }

    public void deletePartOfSpeech(Long id) {
        partOfSpeechRepository.deleteById(id);
    }
}
