package org.example.sensor_server.controller;


import org.example.sensor_server.entity.SensorData;
import org.example.sensor_server.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/get")
public class GetController {

    @Autowired
    SensorDataRepository repository;

    @GetMapping("/all")
    public ResponseEntity<List<SensorData>>  getData(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{nodeId}")
    @ResponseBody
    public ResponseEntity<List<SensorData>> getBynodeId(@PathVariable("nodeId") String nodeId){
        return ResponseEntity.ok(repository.findAllBynodeId(nodeId));

    }
}
