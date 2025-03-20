package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.FlashcardWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardWordRepository extends JpaRepository<FlashcardWord, Long> {
	
}
