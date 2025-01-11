package com.system.Learning_system_springboot.config;

import com.system.Learning_system_springboot.security.JwtHandshakeInterceptor;
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
        config.enableSimpleBroker("/topic"); // The server can send messages to clients subscribed to "/topic"
        config.setApplicationDestinationPrefixes("/app"); // Clients send messages to the server using "/app"
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // WebSocket endpoint
                .setAllowedOrigins("http://localhost:4200")// Allow Angular app running on port 4200
                .addInterceptors(new JwtHandshakeInterceptor())
                .withSockJS(); // Enable SockJS fallback
    }
}