package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Message;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MessagesController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/user/messages", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public String getUserMessages(@RequestParam("userId") String userId) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("Error: User not found."));
        List<Message> receivedMessages=user.getSentMessages();
        return new Gson().toJson(receivedMessages);
    }
}
