package edu.sage.datacommonsdashboard.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // Enable a simple memory-based message broker
        config.setApplicationDestinationPrefixes("/app"); // Prefix for incoming messages
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // WebSocket endpoint for the client to connect
        registry.addEndpoint("/websocket") // WebSocket endpoint path
                .setAllowedOrigins("http://localhost:8080", "https://localhost:8080", "https://data-commons.prototype.ucar.edu") // Match subdomains under trusted-origin.com
                .withSockJS(); // Enable SockJS fallback
    }

}