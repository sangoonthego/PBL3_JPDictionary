package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.Word;
import com.jpdictionary.demo.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    private final String API_URL = "https://jisho.org/api/v1/search/words?keyword="; // API nguồn

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public Optional<Word> getWordByName(String word) {
        return wordRepository.findByWord(word);
    }

    public Word fetchWordFromAPI(String word) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + word;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // TODO: Parse JSON từ API để lấy thông tin
            Word newWord = new Word();
            newWord.setWord(word);
            newWord.setReading("読み方"); // Thay bằng dữ liệu thật từ API
            newWord.setMeaning("Nghĩa của từ"); // Thay bằng dữ liệu thật từ API
            return wordRepository.save(newWord);
        }
        return null;
    }
}
