package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.adapters.LocalDateTimeAdapter;
import com.bezkoder.springjwt.models.Chat;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.ChatRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ChatController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChatRepository chatRepository;

    private final ObjectMapper mapper = new ObjectMapper();

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

    @RequestMapping(value = "/user/chats/new", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public String getUserChatsUsers(Principal principal, @RequestBody String requestBody) {
        try {
            JsonNode rootNode = mapper.readTree(requestBody);
            String username = rootNode.get("username").asText();
            User fromUser = userRepository.findByUsername(principal.getName())
                    .orElseThrow(() -> new RuntimeException("Error: User not found."));
            User withUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Error: User not found."));
            Chat newChat = new Chat(fromUser.getId(), username);
            newChat.setUsers(List.of(fromUser, withUser));
            chatRepository.save(newChat);
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();
            return gson.toJson(principal.getName());
        } catch (Exception exception) {
            return "Invalid request body";
        }

    }

}
