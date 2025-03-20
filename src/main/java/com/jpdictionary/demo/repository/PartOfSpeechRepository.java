package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.PartOfSpeech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartOfSpeechRepository extends JpaRepository<PartOfSpeech, Long> {
	
}
