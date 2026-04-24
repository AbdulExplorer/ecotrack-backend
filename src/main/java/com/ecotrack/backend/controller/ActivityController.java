package com.ecotrack.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecotrack.backend.repository.ActivityRepository;

@RestController
@RequestMapping("/api/activity")
@CrossOrigin(origins = "*")
public class ActivityController 
{

    @Autowired
    private ActivityRepository repo;

    @PostMapping("/add")
    public Map<String, Object> addActivity(@RequestBody Map<String, Object> body) 
    {

        String email = (String) body.get("email");
        String category = (String) body.get("category");
        String subType = (String) body.get("subType");
        double value = Double.parseDouble(body.get("value").toString());
        String date = (String) body.get("date");
        String note = (String) body.get("note");

        // Back end calculation
        double co2 = calculateCO2(category, subType, value);

        // save in DB
        repo.saveActivity(email, category, subType, value, date, note, co2);

        // response return
        return Map.of(
            "co2", co2,
            "message", "Activity Saved!"
        );
    }

    // calculation method
    private double calculateCO2(String category, String subType, double value) 
    {

        double total = 0;

        if (category.equals("Transport")) 
        {
            if (subType.equals("Car")) total = value * 0.21;
            if (subType.equals("Bike/Scooter")) total = value * 0.1;
            if (subType.equals("Bus")) total = value * 0.05;
        }

        if (category.equals("Food")) 
        {
            if (subType.equals("Non-Veg Meal")) total = value * 2;
            if (subType.equals("Veg Meal")) total = value * 1;
        }

        if (category.equals("Electricity")) 
        {
            total = value * 0.5;
        }

        if (category.equals("Waste")) 
        {
            total = value * 1.2;
        }

        // round to 2 decimal
        return Math.round(total * 100.0) / 100.0;
    }
}