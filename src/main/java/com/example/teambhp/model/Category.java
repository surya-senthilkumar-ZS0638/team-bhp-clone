package com.example.teambhp.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    public Category(String name, String description, Set<Thread> threads) {
        this.name = name;
        this.description = description;
        this.threads = threads;
    }

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    public Category() {

    }

    public Category(Long id) {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Thread> getThreads() {
        return threads;
    }

    public void setThreads(Set<Thread> threads) {
        this.threads = threads;
    }

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Thread> threads;

    // Constructors, getters & setters
}
