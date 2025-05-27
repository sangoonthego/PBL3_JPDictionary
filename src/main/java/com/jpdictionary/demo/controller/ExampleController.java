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

    // Fetch từ API ngoài
    @GetMapping("/fetch")
    public List<Example> fetchExamples() {
        return exampleService.getExamplesFromAPI();
    }

    // CREATE
    @PostMapping
    public Example createExample(@RequestBody Example example) {
        return exampleService.saveExample(example);
    }

    // READ ALL
    @GetMapping
    public List<Example> getAllExamples() {
        return exampleService.getAllExamples();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Example getExampleById(@PathVariable Long id) {
        return exampleService.getExampleById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Example updateExample(@PathVariable Long id, @RequestBody Example example) {
        return exampleService.updateExample(id, example);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteExample(@PathVariable Long id) {
        exampleService.deleteExample(id);
    }
}

