package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.Kanji;
import com.jpdictionary.demo.service.KanjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kanji")
public class KanjiController {

    @Autowired
    private KanjiService kanjiService;

    @GetMapping("/fetch")
    public List<Kanji> fetchKanji() {
        return kanjiService.getKanjiFromAPI();
    }
    
    @GetMapping
    public List<Kanji> getAllKanji() {
        return kanjiService.getAllKanji();
    }

    @GetMapping("/{id}")
    public Kanji getKanjiById(@PathVariable Long id) {
        return kanjiService.getKanjiById(id);
    }

    @PostMapping
    public Kanji createKanji(@RequestBody Kanji kanji) {
        return kanjiService.createKanji(kanji);
    }

    @PutMapping("/{id}")
    public Kanji updateKanji(@PathVariable Long id, @RequestBody Kanji kanji) {
        return kanjiService.updateKanji(id, kanji);
    }

    @DeleteMapping("/{id}")
    public String deleteKanji(@PathVariable Long id) {
        kanjiService.deleteKanji(id);
        return "Kanji deleted successfully!";
    }
}
