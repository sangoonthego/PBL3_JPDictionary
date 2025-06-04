package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.SavedWord;
import com.jpdictionary.demo.models.SavedWordId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedWordRepository extends JpaRepository<SavedWord, SavedWordId> {
    List<SavedWord> findByUserId(Long userId);
    List<SavedWord> findByIdUserId(Long userId);
}
