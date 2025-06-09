package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.DailyViewCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DailyViewCountRepository extends JpaRepository<DailyViewCount, LocalDate> {
    @Query(value = "SELECT * FROM daily_view_count WHERE view_date = :viewDate", nativeQuery = true)
    DailyViewCount findByViewDate(@Param("viewDate") LocalDate viewDate);
    @Query(value = "SELECT * FROM daily_view_count", nativeQuery = true)
    List<DailyViewCount> findAllDailyViewCounts();
}