package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.DailyViewCount;
import com.jpdictionary.demo.service.DailyViewCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/daily-views")
public class DailyViewCountController {

    @Autowired
    private DailyViewCountService dailyViewCountService;

    @PostMapping("/increment")
    public DailyViewCount incrementDailyView() {
        return dailyViewCountService.incrementTodayViewCount();
    }

    @GetMapping("/today")
    public DailyViewCount getTodayCount() {
        return dailyViewCountService.getTodayViewCount();
    }

    @GetMapping("/all")
    public List<DailyViewCount> getAllDailyViewCounts() {
        return dailyViewCountService.getAllViewCounts();
    }
}