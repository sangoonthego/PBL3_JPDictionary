package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.WordView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordViewRepository extends JpaRepository<WordView, Long> {
	
}
