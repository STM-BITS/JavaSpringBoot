package com.renitalobo.stmintegrationtesting;

import static org.assertj.core.api.Assertions.assertThat;

import com.renitalobo.stmintegrationtesting.controller.TodoController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private TodoController todoController;

    @Test
    public void todoLoads() throws Exception {
        assertThat(todoController).isNotNull();
    }



}
