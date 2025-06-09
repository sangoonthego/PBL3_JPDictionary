package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.Kanji;
import com.jpdictionary.demo.service.KanjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/kanji")
@CrossOrigin(origins = "http://localhost:3000")
public class KanjiController {

    @Autowired
    private KanjiService kanjiService;

    @GetMapping
    public ResponseEntity<?> getKanjiByLevel(@RequestParam(name = "jlpt_level") int jlptLevel) {
        try {
            List<Kanji> kanjiList = kanjiService.getKanjiByLevel(jlptLevel);
            return ResponseEntity.ok(kanjiList);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> addKanji(@RequestBody Kanji kanji) {
        try {
            Kanji saved = kanjiService.saveKanji(kanji);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateKanji(@PathVariable Long id, @RequestBody Kanji updatedKanji) {
        try {
            Kanji updated = kanjiService.updateKanji(id, updatedKanji);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKanji(@PathVariable Long id) {
        try {
            kanjiService.deleteKanji(id);
            return ResponseEntity.ok(Collections.singletonMap("message", "Xoá Kanji thành công"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("message", e.getMessage()));
        }
    }
}
