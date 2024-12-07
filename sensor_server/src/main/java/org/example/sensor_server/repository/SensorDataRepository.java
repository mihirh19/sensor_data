package org.example.sensor_server.repository;

import org.example.sensor_server.entity.SensorData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SensorDataRepository extends MongoRepository<SensorData, String> {


    List<SensorData> findAllBynodeId(String nodeId);
}
