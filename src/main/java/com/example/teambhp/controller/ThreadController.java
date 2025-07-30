package com.example.teambhp.controller;

import com.example.teambhp.dto.ThreadDto;
import com.example.teambhp.service.ThreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/threads")
@RequiredArgsConstructor
public class ThreadController {
    private final ThreadService threadService;

    @GetMapping
    public ResponseEntity<List<ThreadDto>> getAllThreads() {
        List<ThreadDto> list = threadService.getAllThreads();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThreadDto> getThreadById(@PathVariable Long id) {
        ThreadDto dto = threadService.getThreadById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ThreadDto> createThread(@RequestBody ThreadDto dto) {
        ThreadDto created = threadService.createThread(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
