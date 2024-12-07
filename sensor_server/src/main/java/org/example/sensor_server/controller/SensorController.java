package org.example.sensor_server.controller;


import org.example.sensor_server.entity.SensorData;
import org.example.sensor_server.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class SensorController {

    @Autowired
    private SensorDataRepository repository;

private final SimpMessagingTemplate simpMessagingTemplate;

    public SensorController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/data")
//    @SendTo("/topic/sensor-updates")
    public void handleSensorData(@Payload SensorData data) {

        data.setTimestamp(LocalDateTime.now());
        repository.save(data); // Save to MongoDB
        simpMessagingTemplate.convertAndSend("/topic/sensor-updates/" + data.getNodeId(), data);
//        return data; // Broadcast the received data to all subscribers
    }
}
