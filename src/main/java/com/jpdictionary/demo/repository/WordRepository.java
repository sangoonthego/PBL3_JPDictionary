package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    Optional<Word> findByWord(String word);

    @Query(value = "SELECT * FROM words w " +
            "WHERE LOWER(w.word) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(w.reading) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(w.meaning) LIKE LOWER(CONCAT('%', :query, '%'))",
           nativeQuery = true)
    List<Word> searchWordsNative(@Param("query") String query);

    @Query(value = "SELECT * FROM words WHERE jlpt = :level", nativeQuery = true)
    List<Word> findWordsByJlptLevel(@Param("level") String level);

    @Query(value = "SELECT * FROM words WHERE id IN :ids", nativeQuery = true)
    List<Word> findByIds(@Param("ids") List<Long> ids);
}
