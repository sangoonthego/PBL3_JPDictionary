package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.User;
import com.jpdictionary.demo.service.DailyLookupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lookup")
public class DailyLookupUserController {

    @Autowired
    private DailyLookupUserService service;

    // Ví dụ: user tra từ (giả sử có user object từ session hoặc token)
    @PostMapping("/record")
    public String recordLookup(@RequestBody User user) {
        service.recordUserLookup(user);
        return "Lookup recorded (once per day per user)";
    }

    @GetMapping("/count-today")
    public int getTodayCount() {
        return service.countDistinctUsersToday();
    }
}
