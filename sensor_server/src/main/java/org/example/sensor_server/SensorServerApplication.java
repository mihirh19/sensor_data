package org.example.sensor_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories
public class SensorServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensorServerApplication.class, args);
    }

}
