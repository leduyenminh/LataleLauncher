package com.springdatajpa.demo.interfaces;

import java.time.LocalDateTime;

public interface CharacterProjection {
    String getTodoItem();
    LocalDateTime getCreatedAt();
}
