package com.bezkoder.springjwt.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.User;

import javax.persistence.MapKey;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Optional<User> findById(Long id);
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query(value = "SELECT DISTINCT user_id From users\n" +
			"JOIN chats c on users.id = c.from_user_id\n" +
			"JOIN users_chats uc on c.id = uc.chat_id",
			nativeQuery = true)
	List<Long> findChattingWithUsers(Long id);

	List<User> findByIdIn(List<Long> idsList);

}
