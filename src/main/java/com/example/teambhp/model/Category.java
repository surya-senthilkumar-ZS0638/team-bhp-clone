package com.example.teambhp.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Threads> threads;

    public Category() {}

    public Category(Long id) {
        super();
    }

    public Category(String name, String description, Set<Threads> threads) {
        this.name = name;
        this.description = description;
        this.threads = threads;
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

    public Set<Threads> getThreads() {
        return threads;
    }

    public void setThreads(Set<Threads> threads) {
        this.threads = threads;
    }
}
