package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Dm {
    public static void main(String[] args) {
        String jsonString = "{\"nodeId\":\"node123\",\"temperature\":22.5,\"humidity\":60.0,\"pressure\":1013.25,\"light\":300.0}";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            System.out.println("nodeId: " + jsonNode.path("nodeId").asText());
            System.out.println("temperature: " + jsonNode.path("temperature").asDouble());
            System.out.println("humidity: " + jsonNode.path("humidity").asDouble());
            System.out.println("pressure: " + jsonNode.path("pressure").asDouble());
            System.out.println("light: " + jsonNode.path("light").asDouble());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
