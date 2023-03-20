package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.adapters.LocalDateTimeAdapter;
import com.bezkoder.springjwt.models.Chat;
import com.bezkoder.springjwt.models.Message;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.MessageRepository;
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
public class MessagesController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;


    private final ObjectMapper mapper = new ObjectMapper();


    @RequestMapping(value="/user/messages", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public String getUserMessages(@RequestParam("userId") String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("Error: User not found."));
        List<Message> receivedMessages=user.getSentMessages();
        return new Gson().toJson(receivedMessages);
    }


    @RequestMapping(value = "/user/chat/message", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public String storeMessage(Principal principal, @RequestBody String requestBody) {
        try {
            JsonNode rootNode = mapper.readTree(requestBody);
            Long chatId = rootNode.get("chatId").asLong();
            String message = rootNode.get("message").asText();
            User fromUser = userRepository.findByUsername(principal.getName())
                    .orElseThrow(() -> new RuntimeException("Error: User not found."));
            Message newMessage = new Message(chatId, fromUser.getId(), message);
            messageRepository.save(newMessage);
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
