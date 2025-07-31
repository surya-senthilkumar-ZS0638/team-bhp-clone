package com.example.teambhp.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Threads> getThreads() {
        return threads;
    }

    public void setThreads(Set<Threads> threads) {
        this.threads = threads;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public User(String username, String password, String email, Set<Threads> threads, Set<Post> posts) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.threads = threads;
        this.posts = posts;
    }

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private Set<Threads> threads;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<com.example.teambhp.model.Post> posts;

    public User() {}

}
