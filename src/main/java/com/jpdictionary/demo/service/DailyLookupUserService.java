package com.jpdictionary.demo.service;

import com.jpdictionary.demo.models.DailyLookupUser;
import com.jpdictionary.demo.models.User;
import com.jpdictionary.demo.repository.DailyLookupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DailyLookupUserService {

    @Autowired
    private DailyLookupUserRepository dailyRepo;

    public void recordUserLookup(User user) {
        LocalDate today = LocalDate.now();
        boolean exists = dailyRepo.existsByLookupDateAndUser_Id(today, user.getId());
        if (!exists) {
            dailyRepo.save(new DailyLookupUser(today, user));
        }
    }

    public int countDistinctUsersToday() {
        return dailyRepo.countByLookupDate(LocalDate.now());
    }
}
