package org.example.sensor_server.repository;

import org.example.sensor_server.entity.SensorData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorDataRepository extends MongoRepository<SensorData, String> {
}
