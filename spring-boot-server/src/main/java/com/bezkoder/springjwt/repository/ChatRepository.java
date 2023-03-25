package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
