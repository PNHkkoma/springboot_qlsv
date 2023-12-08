package com.example.qlsv.config;

import com.example.qlsv.handler.WebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketConfig implements WebSocketConfigurer, WebSocketMessageBrokerConfigurer {
    //đăng ký 1 địa chỉ kết nối ws là "/ws", kích hoạt chế độ sockJS
    //sockJS: sử l trường hợp trình duyệt không hỗ trợ truyền dữ liệu
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Sử dụng DefaultHandshakeHandler để xử lý WebSocket handshake
        registry.addEndpoint("/ws")
                .setHandshakeHandler(new DefaultHandshakeHandler())
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/ws");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new WebSocketHandler();
    }
}