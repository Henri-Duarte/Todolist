package com.api.todolist.service;

import com.api.todolist.model.TodoModel;
import com.api.todolist.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    @Autowired
    final TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoModel> findAll(){
        return todoRepository.findAll();
    }

    public Optional<TodoModel> findById(UUID Id){
        return todoRepository.findById(Id);
    }

    @Transactional
    public TodoModel save (@RequestBody TodoModel todoModel){
        return todoRepository.save(todoModel);
    }

    public boolean existsById(UUID Id){
        return todoRepository.existsById(Id);
    }

    public boolean existsByTitle(String Title) {
        return todoRepository.existsByTitle(Title);
    }

    public void delete(TodoModel todoModel){
        todoRepository.delete(todoModel);
    }
}
