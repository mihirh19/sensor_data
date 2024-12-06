package org.example.sensor_server.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class SensorWebSocketHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> nodeSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Placeholder for connection established
        System.out.println("New connection established: " + session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        // Assuming the payload is JSON with a nodeId field
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(payload);

        if (jsonNode.has("nodeId")) {
            String nodeId = jsonNode.get("nodeId").asText();
            // Register the session with the nodeId
            nodeSessions.put(nodeId, session);
            System.out.println("Node registered: " + nodeId);
        } else if (jsonNode.has("request")) {
            // Handle data requests from the server
            String nodeId = jsonNode.get("nodeId").asText();
            String request = jsonNode.get("request").asText();

            // Send a request to the specific node
            WebSocketSession nodeSession = nodeSessions.get(nodeId);
            if (nodeSession != null && nodeSession.isOpen()) {
                nodeSession.sendMessage(new TextMessage("Server Request: " + request));
            } else {
                System.out.println("Node not available: " + nodeId);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Remove the session on connection close
        nodeSessions.values().removeIf(session::equals);
        System.out.println("Connection closed: " + session.getId());
    }

    public void broadcastMessage(String message) {
        // Broadcast a message to all connected nodes
        nodeSessions.values().forEach(session -> {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
