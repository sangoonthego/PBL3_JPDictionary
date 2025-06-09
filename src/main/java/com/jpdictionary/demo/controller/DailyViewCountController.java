package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.DailyViewCount;
import com.jpdictionary.demo.service.DailyViewCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/daily-views")
public class DailyViewCountController {

    @Autowired
    private DailyViewCountService dailyViewCountService;

    @PostMapping("/increment")
    public ResponseEntity<DailyViewCount> incrementDailyView() {
        DailyViewCount updatedCount = dailyViewCountService.incrementTodayViewCount();
        if (updatedCount != null) {
            return ResponseEntity.ok(updatedCount);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/today")
    public ResponseEntity<DailyViewCount> getTodayCount() {
        DailyViewCount todayCount = dailyViewCountService.getTodayViewCount();
        if (todayCount != null) {
            return ResponseEntity.ok(todayCount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DailyViewCount>> getAllDailyViewCounts() {
        List<DailyViewCount> allCounts = dailyViewCountService.getAllViewCounts();
        if (allCounts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allCounts);
    }
}
