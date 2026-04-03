package com.aryan.validator.controller;

import com.aryan.validator.model.ValidationError;
import com.aryan.validator.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CSVController {

    @Autowired
    private CSVService csvService;

    // ✅ Test API
    @GetMapping("/test")
    public String test() {
        return "API Working";
    }

    // ✅ Upload API
    @PostMapping("/upload")
    public List<ValidationError> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            return csvService.validateCSV(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}