package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.Example;
import com.jpdictionary.demo.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examples")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @GetMapping("/fetch")
    public List<Example> fetchExamples() {
        return exampleService.getExamplesFromAPI();
    }
}
