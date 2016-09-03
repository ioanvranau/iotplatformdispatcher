package com.platform.iot.dispatcher.utils;

/**
 * Created by ioan.vranau on 9/2/2016.
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/iot");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        final StompWebSocketEndpointRegistration stompWebSocketEndpointRegistration = registry.addEndpoint("*", "/iot-dispatcher-websocket");
        stompWebSocketEndpointRegistration.setAllowedOrigins("*");
        stompWebSocketEndpointRegistration.withSockJS();
    }

}