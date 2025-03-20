package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.StudyProgress;
import com.jpdictionary.demo.repository.StudyProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyProgressService {

    @Autowired
    private StudyProgressRepository studyProgressRepository;

    public List<StudyProgress> getAllProgress() {
        return studyProgressRepository.findAll();
    }

    public Optional<StudyProgress> getProgressById(Long id) {
        return studyProgressRepository.findById(id);
    }

    public StudyProgress saveProgress(StudyProgress studyProgress) {
        return studyProgressRepository.save(studyProgress);
    }

    public StudyProgress updateProgress(Long id, StudyProgress newProgress) {
        return studyProgressRepository.findById(id).map(progress -> {
            progress.setLastReviewed(newProgress.getLastReviewed());
            progress.setReviewCount(newProgress.getReviewCount());
            progress.setProficiencyLevel(newProgress.getProficiencyLevel());
            return studyProgressRepository.save(progress);
        }).orElse(null);
    }

    public void deleteProgress(Long id) {
        studyProgressRepository.deleteById(id);
    }
}
