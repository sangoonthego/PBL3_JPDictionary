package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
	
}