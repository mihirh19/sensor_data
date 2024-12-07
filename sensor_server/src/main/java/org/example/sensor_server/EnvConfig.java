package org.example.sensor_server;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class EnvConfig {

    public static void mongoUri(){
        Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("MONGO_URI", dotenv.get("MONGO_URI"));
    }

}
