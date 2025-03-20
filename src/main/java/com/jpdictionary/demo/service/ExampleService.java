package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.Example;
import com.jpdictionary.demo.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    private final String API_URL = "https://api.example.com/examples"; // Thay bằng API thật

    public List<Example> getExamplesFromAPI() {
        RestTemplate restTemplate = new RestTemplate();
        Example[] examples = restTemplate.getForObject(API_URL, Example[].class);
        
        if (examples != null) {
            exampleRepository.saveAll(List.of(examples));  // Lưu vào database
        }
        
        return exampleRepository.findAll();
    }
}
