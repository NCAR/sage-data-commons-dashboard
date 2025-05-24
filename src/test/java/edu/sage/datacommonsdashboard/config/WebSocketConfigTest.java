package edu.sage.datacommonsdashboard.config;

import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.SockJsServiceRegistration;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;

import static org.mockito.Mockito.*;

public class WebSocketConfigTest {

    @Test
    void testConfigureMessageBroker() {

        MessageBrokerRegistry messageBrokerRegistry = mock(MessageBrokerRegistry.class);
        String allowedOrigins = "http://example.com,http://another.com";
        WebSocketConfig config = new WebSocketConfig(allowedOrigins);

        config.configureMessageBroker(messageBrokerRegistry);

        verify(messageBrokerRegistry).enableSimpleBroker("/topic"); // Verifies if "/topic" is added
        verify(messageBrokerRegistry).setApplicationDestinationPrefixes("/app"); // Verifies "/app" prefix
    }

    // @Test
    void testRegisterStompEndpoints() {

        StompEndpointRegistry mockStompEndpointRegistry = mock(StompEndpointRegistry.class);
        StompWebSocketEndpointRegistration mockStompWebSocketEndpointRegistration= mock(StompWebSocketEndpointRegistration.class);
        String allowedOrigins = "http://example.com,http://another.com";
        SockJsServiceRegistration sockJsServiceRegistration = mock(SockJsServiceRegistration.class);

        WebSocketConfig config = new WebSocketConfig(allowedOrigins);

        // Mock chainable method calls for StompEndpointRegistry
        when(mockStompEndpointRegistry.addEndpoint("/websocket")).thenReturn(mockStompWebSocketEndpointRegistration);
        when(mockStompWebSocketEndpointRegistration.setAllowedOrigins(any())).thenReturn(mockStompWebSocketEndpointRegistration);
        when(mockStompWebSocketEndpointRegistration.withSockJS()).thenReturn(sockJsServiceRegistration);

        // Test
        config.registerStompEndpoints(mockStompEndpointRegistry);

        verify(mockStompEndpointRegistry).addEndpoint("/websocket"); // Verify endpoint registration
        verify(mockStompWebSocketEndpointRegistration).setAllowedOrigins("http://example.com", "http://another.com"); // Correct splitting of allowedOrigins
        verify(mockStompWebSocketEndpointRegistration).withSockJS(); // Check if SockJS is enabled
    }
}
