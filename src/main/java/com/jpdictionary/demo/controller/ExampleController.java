package com.jpdictionary.demo.controller;

import com.jpdictionary.demo.models.Example;
import com.jpdictionary.demo.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/examples")
@CrossOrigin(origins = "http://localhost:3000")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    // Fetch từ API ngoài
    @GetMapping("/fetch")
    public ResponseEntity<List<Example>> fetchExamples() {
        List<Example> examples = exampleService.getExamplesFromAPI();
        return ResponseEntity.ok(examples);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> createExample(@RequestBody Example example) {
        try {
            Example saved = exampleService.saveExample(example);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Example>> getAllExamples() {
        List<Example> examples = exampleService.getAllExamples();
        return ResponseEntity.ok(examples);
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<?> getExampleById(@PathVariable Long id) {
        Example example = exampleService.getExampleById(id);
        if (example != null) {
            return ResponseEntity.ok(example);
        } else {
            return ResponseEntity.status(404).body(Collections.singletonMap("message", "Không tìm thấy ví dụ"));
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExample(@PathVariable Long id, @RequestBody Example example) {
        try {
            Example updated = exampleService.updateExample(id, example);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExample(@PathVariable Long id) {
        try {
            exampleService.deleteExample(id);
            return ResponseEntity.ok(Collections.singletonMap("message", "Xoá ví dụ thành công"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("message", e.getMessage()));
        }
    }
}
