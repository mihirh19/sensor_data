package org.example.sensor_server.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document()
@Getter
@Setter
@NoArgsConstructor
public class SensorData {
    @Id
    private String id;


    private String nodeId;

    private double temperature;

    private double humidity;

    private double pressure;

    private double light;
    private LocalDateTime timestamp;

//    @JsonCreator
//    public SensorData(
//            @JsonProperty("nodeId") String nodeId,
//            @JsonProperty("temperature") double temperature,
//            @JsonProperty("humidity") double humidity,
//            @JsonProperty("pressure") double pressure,
//            @JsonProperty("light") double light) {
//            this.nodeId  = nodeId;
//            this.temperature = temperature;
//            this.humidity = humidity;
//            this.light = light;
//            this.pressure = pressure;
//    }
}
