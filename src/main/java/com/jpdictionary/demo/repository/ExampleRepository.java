package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
    List<Example> findByWord_Id(Long wordId);  // Note the underscore here for nested property
}
