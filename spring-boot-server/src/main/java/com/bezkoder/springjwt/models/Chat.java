package com.bezkoder.springjwt.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="from_user_id")
    @NotNull
    private Long from_user_id;

    @OneToMany
    @JoinColumn(name = "chat_id")
    private List<Message> messages = new ArrayList<>();

    //    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(	name = "users_chats",
//            joinColumns = @JoinColumn(name = "chat_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection
    @CollectionTable(name = "users_chats", joinColumns = @JoinColumn(name = "chat_id"))
    @Column(name = "user_id")
    private List<Long> users = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Chat(Long from_user_id, String name) {
        this.from_user_id = from_user_id;
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Chat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsers(List<User> users) {
        this.users = users.stream()
                .map(User::getId)
                .collect(Collectors.toList());
        for (User user : users) {
            user.getChats().add(this);
        }
    }


}
