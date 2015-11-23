package com.example.jms.domain.repository;


import java.util.Collection;

import com.example.jms.domain.model.Todo;


public interface TodoRepository {
    Todo findOne(String todoId);

    Collection<Todo> findAll();

    void create(Todo todo);

    boolean update(Todo todo);

    void delete(Todo todo);

    long countByFinished(boolean finished);
}