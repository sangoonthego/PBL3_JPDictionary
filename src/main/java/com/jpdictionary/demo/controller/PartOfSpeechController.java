package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.PartOfSpeech;
import com.jpdictionary.demo.service.PartOfSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/part-of-speech")
public class PartOfSpeechController {

    @Autowired
    private PartOfSpeechService partOfSpeechService;

    @GetMapping("/fetch")
    public List<PartOfSpeech> fetchPartsOfSpeech() {
        return partOfSpeechService.getPartsOfSpeechFromAPI();
    }
}
