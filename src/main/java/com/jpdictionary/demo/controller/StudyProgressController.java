package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.StudyProgress;
import com.jpdictionary.demo.service.StudyProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/study-progress")
public class StudyProgressController {

    @Autowired
    private StudyProgressService studyProgressService;

    @GetMapping
    public List<StudyProgress> getAllProgress() {
        return studyProgressService.getAllProgress();
    }

    @GetMapping("/{id}")
    public Optional<StudyProgress> getProgressById(@PathVariable Long id) {
        return studyProgressService.getProgressById(id);
    }

    @PostMapping
    public StudyProgress createProgress(@RequestBody StudyProgress studyProgress) {
        return studyProgressService.saveProgress(studyProgress);
    }

    @PutMapping("/{id}")
    public StudyProgress updateProgress(@PathVariable Long id, @RequestBody StudyProgress newProgress) {
        return studyProgressService.updateProgress(id, newProgress);
    }

    @DeleteMapping("/{id}")
    public void deleteProgress(@PathVariable Long id) {
        studyProgressService.deleteProgress(id);
    }
}
