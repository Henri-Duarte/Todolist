package com.api.todolist.controller;


import com.api.todolist.TodoDTO.TodoDTO;
import com.api.todolist.model.TodoModel;
import com.api.todolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/list")
public class TodoController {

    @Autowired
    final TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoModel>> getList(){
        List<TodoModel> allList = todoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByIdList(UUID Id){
        Optional<TodoModel> todoModelOptional = todoService.findById(Id);
        if (!todoModelOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id invalido");
        }
        return ResponseEntity.status(HttpStatus.OK).body(todoModelOptional);
    }

    @PostMapping
    public ResponseEntity<Object> saveList(@RequestBody @Valid TodoDTO todoDTO){
        if (todoService.existsByTitle(todoDTO.getTitle())){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Título já existente.");
        }
        var todoModel = new TodoModel(todoDTO);
        BeanUtils.copyProperties(todoDTO, todoModel);
        todoModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        TodoModel newTodo = todoService.save(todoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putList(@PathVariable(value = "id") UUID Id,
                                          @RequestBody TodoDTO todoDTO){
        Optional<TodoModel> todoModelOptional = todoService.findById(Id);
        if (!todoModelOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id invalido.");
        }
        var todoModel = new TodoModel(todoDTO);
        BeanUtils.copyProperties(todoDTO, todoModel);
        todoModel.setEditDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(todoService.save(todoModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteList(@PathVariable(value = "id") UUID Id,
                                             TodoModel todoModel){
        Optional<TodoModel> todoModelOptional = todoService.findById(Id);
        if (!todoModelOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id invalido");
        }
        todoService.delete(todoModel);
        return ResponseEntity.status(HttpStatus.OK).body("Deletado da lista");
    }

}
