package com.example.todolist.repository;

import com.example.todolist.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long>{}
