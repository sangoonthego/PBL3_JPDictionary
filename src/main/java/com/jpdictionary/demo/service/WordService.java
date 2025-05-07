package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.Word;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpdictionary.demo.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Date;
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
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = API_URL + word;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody());
                JsonNode dataNode = root.path("data");
                
                if (!dataNode.isArray() || dataNode.size() == 0) return null;

                JsonNode firstEntry = dataNode.get(0);
                JsonNode japaneseArray = firstEntry.path("japanese");
                JsonNode sensesArray = firstEntry.path("senses");

                String reading = japaneseArray.get(0).path("reading").asText("");
                String meaning = sensesArray.get(0).path("english_definitions").get(0).asText("");

                Word newWord = new Word();
                newWord.setWord(word);
                newWord.setReading(reading);
                newWord.setMeaning(meaning);
                newWord.setCreatedAt(new Date());

                return wordRepository.save(newWord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Word saveWord(Word word) {
        word.setCreatedAt(new Date()); // nếu muốn tự thêm createdAt
        return wordRepository.save(word);
    }
    
    public Optional<Word> getWordById(Long id) {
        return wordRepository.findById(id);
    }

    public Optional<Word> updateWord(Long id, Word updatedWord) {
        return wordRepository.findById(id).map(existing -> {
            existing.setWord(updatedWord.getWord());
            existing.setReading(updatedWord.getReading());
            existing.setMeaning(updatedWord.getMeaning());
            return wordRepository.save(existing);
        });
    }

    public boolean deleteWord(Long id) {
        if (wordRepository.existsById(id)) {
            wordRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
