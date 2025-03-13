package edu.sage.datacommonsdashboard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private String allowedOrigins;

    // Constructor injection for the allowedOrigins
    public WebSocketConfig(@Value("${allowed.origins}") String allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // Enable a simple memory-based message broker
        config.setApplicationDestinationPrefixes("/app"); // Prefix for incoming messages
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        String[] origins = allowedOrigins.split(",");

        // WebSocket endpoint for the client to connect
        registry.addEndpoint("/websocket") // WebSocket endpoint path
                .setAllowedOrigins(origins) // Match subdomains under trusted-origin.com
                .withSockJS(); // Enable SockJS fallback
    }
}