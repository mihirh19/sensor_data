package org.example.sensor_server.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collation = "sensor_data")
@Getter
@Setter
public class SensorData {
    @Id
    private String id;
    private String nodeId;
    private double temperature;
    private double humidity;
    private double pressure;
    private double light;
    private LocalDateTime timestamp;
}
