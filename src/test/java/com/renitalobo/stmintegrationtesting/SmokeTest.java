package com.renitalobo.stmintegrationtesting;

import static org.assertj.core.api.Assertions.assertThat;

import com.renitalobo.stmintegrationtesting.controller.TodoController;
import com.renitalobo.stmintegrationtesting.controller.TodoListController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private TodoController todoController;
    private TodoListController todoListController;

    @Test
    public void todoListLoads() throws Exception {
        assertThat(todoListController).isNull();
    }
    @Test
    public void todoLoads() throws Exception {
        assertThat(todoController).isNotNull();
    }



}
