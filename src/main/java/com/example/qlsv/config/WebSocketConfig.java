package com.example.qlsv.config;

import com.example.qlsv.handler.WebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketConfigurer, WebSocketMessageBrokerConfigurer {


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(customWebSocketHandler(), "/ws")
                .setAllowedOrigins("*");
                //.setHandshakeHandler();
    }


    @Bean
    public WebSocketHandler customWebSocketHandler() {
        return new WebSocketHandler();
    }

    @Bean
    public DefaultHandshakeHandler handshakeHandler() {
        return new DefaultHandshakeHandler();
    }

    @Bean
    public WebSocketMessageBrokerConfigurer webSocketMessageBrokerConfigurer() {
        return new WebSocketMessageBrokerConfigurer() {
            @Override
            public void configureMessageBroker(MessageBrokerRegistry registry) {
                registry.setApplicationDestinationPrefixes("/app");
                registry.enableSimpleBroker("/topic");
            }
            @Override
            public void registerStompEndpoints(StompEndpointRegistry registry) {
                registry.addEndpoint("/ws")
                        .setAllowedOrigins("*")
                        .setHandshakeHandler(handshakeHandler())
                        .withSockJS();
            }
        };
    }
}
