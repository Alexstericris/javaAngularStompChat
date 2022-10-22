package com.bezkoder.springjwt.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="from_user_id")
    @NotNull
    private Long from_user_id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="chat_id")
    @NotNull
    private Long chat_id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String message;

    public Long getFromUserId() {
        return from_user_id;
    }

    public void setFromUserId(Long from_user_id) {
        this.from_user_id = from_user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public LocalDateTime getCreateAt() {
//        return createAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
}
