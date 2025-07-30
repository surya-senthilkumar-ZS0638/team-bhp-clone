package com.example.teambhp.repository;

import com.example.teambhp.model.Threads;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepository extends JpaRepository<Threads, Long> {}