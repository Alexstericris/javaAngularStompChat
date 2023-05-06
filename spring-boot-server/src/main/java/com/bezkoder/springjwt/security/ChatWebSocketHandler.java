//package com.bezkoder.springjwt.security;
//
//import org.springframework.web.socket.*;
//
//public class ChatWebSocketHandler implements WebSocketHandler {
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        // Code to execute when a WebSocket connection is established
//        System.out.println("testing");
//    }
//
//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        // Code to execute when a text message is received over the WebSocket connection
//        System.out.println("testing2");
//
//    }
//
//    @Override
//    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
//
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
//
//    }
//
//    @Override
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//}
