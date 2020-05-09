package com.renitalobo.stmintegrationtesting;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renitalobo.stmintegrationtesting.controller.TodoController;
import com.renitalobo.stmintegrationtesting.model.Todo;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class TestingWebApplication {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void createServiceTest() throws Exception {
            String uri = "http://localhost:8080/create";
            Todo todo = new Todo();
            todo.setTaskName("Go for a walk!");

            String inputJson = mapToJson(todo);
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            String content = mvcResult.getResponse().getContentAsString();
            Todo todoreturn = mapFromJson(content, Todo.class);
            assertEquals(todoreturn.getTaskName(), todo.getTaskName());
    }

    @Test
    public void getAllTodosServiceTest() throws Exception {

        String uri = "http://localhost:8080/create";
        Todo todo = new Todo();
        todo.setTaskName("Go for a walk!");

        String inputJson = mapToJson(todo);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Todo todoreturn = mapFromJson(content, Todo.class);

        uri = "http://localhost:8080/getAllTodos";
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        content = mvcResult.getResponse().getContentAsString();
        Todo[] todolist = mapFromJson(content, Todo[].class);
        assertEquals(1, todolist.length);
        assertEquals(todolist[0].getUuid(),todoreturn.getUuid());
        assertEquals(todolist[0].getTaskName(),todoreturn.getTaskName());
    }

    @Test
    public void deleteServiceTest() throws Exception {

        String uri = "http://localhost:8080/create";
        Todo todo = new Todo();
        todo.setTaskName("Go for a walk!");
        String inputJson = mapToJson(todo);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Todo todolist = mapFromJson(content, Todo.class);

        uri = "http://localhost:8080/delete/"+todolist.getUuid();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        content = mvcResult.getResponse().getContentAsString();
        assertEquals( "Deleted", content);
    }
}

