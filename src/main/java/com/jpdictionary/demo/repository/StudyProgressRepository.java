package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.StudyProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyProgressRepository extends JpaRepository<StudyProgress, Long> {
	
}
