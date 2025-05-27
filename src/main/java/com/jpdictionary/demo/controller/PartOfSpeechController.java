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
    
    @GetMapping
    public List<PartOfSpeech> getAllPartsOfSpeech() {
        return partOfSpeechService.getAllPartsOfSpeech();
    }

    @GetMapping("/{id}")
    public PartOfSpeech getPartOfSpeechById(@PathVariable Long id) {
        return partOfSpeechService.getPartOfSpeechById(id);
    }

    @PostMapping
    public PartOfSpeech createPartOfSpeech(@RequestBody PartOfSpeech pos) {
        return partOfSpeechService.createPartOfSpeech(pos);
    }

    @PutMapping("/{id}")
    public PartOfSpeech updatePartOfSpeech(@PathVariable Long id, @RequestBody PartOfSpeech pos) {
        return partOfSpeechService.updatePartOfSpeech(id, pos);
    }

    @DeleteMapping("/{id}")
    public String deletePartOfSpeech(@PathVariable Long id) {
        partOfSpeechService.deletePartOfSpeech(id);
        return "Part of speech deleted successfully!";
    }
}
