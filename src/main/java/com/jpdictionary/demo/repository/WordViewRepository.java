package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.WordView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WordViewRepository extends JpaRepository<WordView, Long> {

    @Modifying
    @Transactional
    @Query(value = """
        MERGE INTO word_views AS target
        USING (SELECT ?1 AS word_id) AS source
        ON target.word_id = source.word_id
        WHEN MATCHED THEN 
            UPDATE SET view_count = target.view_count + 1
        WHEN NOT MATCHED THEN
            INSERT (word_id, view_count) VALUES (source.word_id, 1);
        """, nativeQuery = true)
    void upsertAndIncrementView(Long wordId);

    @Query(value = "SELECT * FROM word_views ORDER BY view_count DESC", nativeQuery = true)
    List<WordView> findAllByViewCount();

    // Retrieve top 18 WordView records by view_count
    @Query(value = "SELECT TOP 12 * FROM word_views ORDER BY view_count DESC", nativeQuery = true)
    List<WordView> findTop18ByViewCount();
}
