package com.api.todolist.repository;

import com.api.todolist.model.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<TodoModel, UUID> {

    boolean existsById(UUID uuid);
    boolean existsByTitle(String title);

}