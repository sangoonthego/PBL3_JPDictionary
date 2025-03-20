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
}
