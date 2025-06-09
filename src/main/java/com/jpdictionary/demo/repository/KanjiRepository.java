package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.Kanji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface KanjiRepository extends JpaRepository<Kanji, Long> {

    @Query(value = "SELECT * FROM kanji WHERE jlpt_level = :level", nativeQuery = true)
    List<Kanji> findKanjiByJlptLevel(@Param("level") int level);
}
