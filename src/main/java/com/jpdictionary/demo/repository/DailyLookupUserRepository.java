package com.jpdictionary.demo.repository;

import com.jpdictionary.demo.models.DailyLookupUser;
import com.jpdictionary.demo.models.DailyLookupUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface DailyLookupUserRepository extends JpaRepository<DailyLookupUser, DailyLookupUserId> {
    
    boolean existsByLookupDateAndUser_Id(LocalDate lookupDate, Long userId);

    @Query("SELECT COUNT(d) FROM DailyLookupUser d WHERE d.lookupDate = :date")
    int countByLookupDate(LocalDate date);
}
