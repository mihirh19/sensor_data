package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ExecutionException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private final String nodeId;
    private final WebSocketStompClient stompClient;
    private StompSession session;
    private final Random random = new Random();

    public Main(String nodeId, URI serverUri) {
        this.nodeId = nodeId;
        this.stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        messageConverter.setObjectMapper(objectMapper);
        this.stompClient.setMessageConverter(messageConverter);
    }
    public void connect(URI serverUri) throws Exception {
        StompSessionHandler sessionHandler = new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                System.out.println("Connected to server.");
                session.subscribe("/topic/sensor-updates/" + nodeId, new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(StompHeaders headers) {
                        return SensorData.class; // Type of message received
                    }

                    @Override
                    public void handleFrame(StompHeaders headers, Object payload) {
                        SensorData data = (SensorData) payload;
                        System.out.println("Received data: " + data);
                    }
                });
            }

            @Override
            public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
                exception.printStackTrace();
            }

            @Override
            public void handleTransportError(StompSession session, Throwable exception) {
                exception.printStackTrace();
            }
        };

        session = stompClient.connectAsync(serverUri.toString(), new WebSocketHttpHeaders(), sessionHandler).get();
    }

    public void sendData() {
        SensorData data = new SensorData(nodeId, random.nextDouble() * 50, random.nextDouble() * 100, random.nextDouble() * 100,random.nextDouble() * 100,LocalDateTime.now());
        session.send("/app/data", data);
        System.out.println("Data sent: " + data);
    }
    public static void main(String[] args) throws Exception {
        URI serverUri = new URI(System.getenv("SERVER_URL"));
        String nodeId = args.length > 0 ? args[0] : "node-1";
        Main sensorNode = new Main(nodeId, serverUri);
        sensorNode.connect(serverUri);

        while (true) {

            sensorNode.sendData();
            Thread.sleep(80000);
        }
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    static class SensorData {
        private String nodeId;
        private double temperature;
        private double humidity;
        private double pressure;
        private double light;
        private LocalDateTime timestamp;

        @Override
        public String toString() {
            return "SensorData{" +
                    "nodeId='" + nodeId + '\'' +
                    ", temperature=" + temperature +
                    ", humidity=" + humidity +
                    ", timestamp=" + timestamp +
                    '}';
        }
        // Getters and Setters
    }
}