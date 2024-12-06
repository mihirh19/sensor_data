package org.example.sensor_server.controller;


import org.example.sensor_server.entity.SensorData;
import org.example.sensor_server.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {
    @Autowired
    private SensorDataRepository repository;

    @PostMapping("/submit")
    public ResponseEntity<String> saveSensorData(@RequestBody SensorData data){
        data.setTimestamp(LocalDateTime.now());
        repository.save(data);
        return ResponseEntity.ok("Data saved successfully");
    }
}
