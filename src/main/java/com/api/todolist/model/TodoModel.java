package com.api.todolist.model;

import com.api.todolist.TodoDTO.TodoDTO;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_TODOLIST_OBJECT")
public class TodoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @CreatedDate
    @Column(name = "creationDate")
    private LocalDateTime creationDate;
    @Column(name = "dueDate")
    private LocalDateTime dueDate;
    @Column(name = "editDate")
    private LocalDateTime editDate;
    @Column(name = "complete", nullable = false)
    private boolean complete;

    public TodoModel(){}

    public TodoModel(TodoDTO todoDTO){
        this.title = todoDTO.getTitle();
        this.description = todoDTO.getDescription();
        this.dueDate = todoDTO.getDueDate().atStartOfDay();
        this.complete = todoDTO.isComplete();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(LocalDateTime editDate) {
        this.editDate = editDate;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

}