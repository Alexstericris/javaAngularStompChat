package com.bezkoder.springjwt.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

////import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//
@Configuration
@EnableWebSocket
public class WebSocketConfig {
//        implements WebSocketMessageBrokerConfigurer  {
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry
//                                               registry) {
//        registry.addEndpoint("/mywebsockets")
//                .setAllowedOrigins("mydomain.com").withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config){
//        config.enableSimpleBroker("/topic/", "/queue/");
//        config.setApplicationDestinationPrefixes("/app");
//    }
}
