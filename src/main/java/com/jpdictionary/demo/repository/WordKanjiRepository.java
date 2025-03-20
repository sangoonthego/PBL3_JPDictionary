package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.WordKanji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordKanjiRepository extends JpaRepository<WordKanji, Long> {
	
}
