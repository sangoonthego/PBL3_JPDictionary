package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.PartOfSpeech;
import com.jpdictionary.demo.service.PartOfSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/part-of-speech")
public class PartOfSpeechController {

    @Autowired
    private PartOfSpeechService partOfSpeechService;

    @GetMapping("/fetch")
    public ResponseEntity<List<PartOfSpeech>> fetchPartsOfSpeech() {
        List<PartOfSpeech> list = partOfSpeechService.getPartsOfSpeechFromAPI();
        return ResponseEntity.ok(list);
    }
    
    @GetMapping
    public ResponseEntity<List<PartOfSpeech>> getAllPartsOfSpeech() {
        List<PartOfSpeech> list = partOfSpeechService.getAllPartsOfSpeech();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPartOfSpeechById(@PathVariable Long id) {
        PartOfSpeech pos = partOfSpeechService.getPartOfSpeechById(id);
        if (pos != null) {
            return ResponseEntity.ok(pos);
        } else {
            return ResponseEntity.status(404)
                    .body(Collections.singletonMap("message", "PartOfSpeech not found"));
        }
    }

    @PostMapping
    public ResponseEntity<PartOfSpeech> createPartOfSpeech(@RequestBody PartOfSpeech pos) {
        PartOfSpeech created = partOfSpeechService.createPartOfSpeech(pos);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePartOfSpeech(@PathVariable Long id, @RequestBody PartOfSpeech pos) {
        try {
            PartOfSpeech updated = partOfSpeechService.updatePartOfSpeech(id, pos);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404)
                    .body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePartOfSpeech(@PathVariable Long id) {
        try {
            partOfSpeechService.deletePartOfSpeech(id);
            return ResponseEntity.ok(Collections.singletonMap("message", "PartOfSpeech deleted successfully!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404)
                    .body(Collections.singletonMap("message", e.getMessage()));
        }
    }
}

