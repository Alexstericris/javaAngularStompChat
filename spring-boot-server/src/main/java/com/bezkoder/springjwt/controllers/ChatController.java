package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.adapters.LocalDateTimeAdapter;
import com.bezkoder.springjwt.models.Chat;
import com.bezkoder.springjwt.models.Message;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ChatController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user/chats", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public String getUserChats(@RequestParam("userId") String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("Error: User not found."));
        List<Chat> receivedMessages = user.getChats();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        return gson.toJson(receivedMessages);
    }

    @RequestMapping(value = "/user/chats/users", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public String getUserChatsUsers(@RequestParam("userId") String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("Error: User not found."));
        List<Long> chattingUserIds = userRepository.findChattingWithUsers(user.getId());
        List<User> chattingUsers = userRepository.findByIdIn(chattingUserIds);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        return gson.toJson(chattingUsers);
    }

    @MessageMapping("/message")
    @SendTo("/chatting/channel")
    public String onMessage(String message) {
        return message;
    }
}
