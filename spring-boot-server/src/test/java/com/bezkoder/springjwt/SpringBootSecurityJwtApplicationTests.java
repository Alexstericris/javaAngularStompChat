package com.bezkoder.springjwt;

import com.bezkoder.springjwt.adapters.LocalDateTimeAdapter;
import com.bezkoder.springjwt.models.Chat;
import com.bezkoder.springjwt.models.Message;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.MessageRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSecurityJwtApplicationTests {

	@Autowired
	UserRepository userRepository;
	@Autowired
	MessageRepository messageRepository;
	@Test
	public void contextLoads() {
	}

	@Test
	@Transactional
	public void userMessage(){
//		User firstUser= userRepository.findByUsername("scristea")
//				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + "scristea"));
//		User secondUser= userRepository.findByUsername("banana")
//				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + "banana"));
//
//		List<Chat> chats=firstUser.getChats();
//
//		Gson gson = new GsonBuilder()
//				.setPrettyPrinting()
//				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
//				.create();
//
//
//		String test = gson.toJson(LocalDateTime.now());
//		String test2 = gson.toJson(chats);
//		System.out.println("testGson");
	}

	@Test
	@Transactional
	public void databaseQuery() {
		List<Long> userIds = userRepository.findChattingWithUsers(Long.getLong("1"));
		System.out.println("testGson");

	}


}
