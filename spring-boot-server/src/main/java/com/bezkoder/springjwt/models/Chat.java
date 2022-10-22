package com.bezkoder.springjwt.models;

import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @JoinColumn(name="chat_id")
    private List<Message> messages=new ArrayList<>();

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(	name = "users_chats",
//            joinColumns = @JoinColumn(name = "chat_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection
    @CollectionTable(name="users_chats",joinColumns = @JoinColumn(name = "chat_id"))
    @Column(name = "user_id")
    private List<Long> users = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
