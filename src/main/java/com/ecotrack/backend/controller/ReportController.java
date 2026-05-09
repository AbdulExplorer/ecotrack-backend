package com.ecotrack.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecotrack.backend.repository.ActivityRepository;

import java.util.*;

@RestController
@RequestMapping("/api/report")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ActivityRepository repo;

    @GetMapping("/user/{email}")
    public List<Map<String, Object>> getUserReport(@PathVariable String email) {
        return repo.getUserActivities(email);
    }
}