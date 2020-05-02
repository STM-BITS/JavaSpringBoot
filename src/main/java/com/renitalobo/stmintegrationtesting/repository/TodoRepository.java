package com.renitalobo.stmintegrationtesting.repository;

import com.renitalobo.stmintegrationtesting.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {

    public List<Todo> findAllByTodoListId(String todoListId);

    public String deleteByUuid(String uid);
}
