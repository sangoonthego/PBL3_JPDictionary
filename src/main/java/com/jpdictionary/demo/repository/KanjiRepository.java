package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.Kanji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KanjiRepository extends JpaRepository<Kanji, Long> {
	
}
