package com.renitalobo.stmintegrationtesting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renitalobo.stmintegrationtesting.controller.TodoController;
import com.renitalobo.stmintegrationtesting.model.Todo;
import com.renitalobo.stmintegrationtesting.services.TodoService;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
@ActiveProfiles("test")
public class MockitoTesting {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    TodoService todoService;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void get_alltodos_returnsOkWithListOfTodos() throws Exception {

        List<Todo> todoList = new ArrayList<>();
        Todo todo1 = new Todo();
        todo1.setUuid("AD23E5R98EFT3SL00");
        todo1.setTaskName("Go for a Walk!");
        Todo todo2 = new Todo();
        todo2.setTaskName("Go for Swimming!");
        todo2.setUuid("O90DEPADE564W4W83");
        todoList.add(todo1);
        todoList.add(todo2);

        Mockito.when(todoService.getAllTodosForList()).thenReturn(todoList);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getAllTodos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Todo[] todolist = mapFromJson(content, Todo[].class);
        assertTrue(todolist.length > 0);
        assertEquals("AD23E5R98EFT3SL00", todolist[0].getUuid());
        assertEquals("Go for a Walk!", todolist[0].getTaskName());
        assertEquals("O90DEPADE564W4W83", todolist[1].getUuid());
        assertEquals("Go for Swimming!", todolist[1].getTaskName());
    }

    @Test
    public void post_createsNewTodo_andReturnsObjWith200() throws Exception {
        Todo todo = new Todo();
        todo.setUuid("AD23E5R98EFT3SL00");
        todo.setTaskName("Go for a Walk!");

        Mockito.when(todoService.addTodo(Mockito.any(Todo.class))).thenReturn(todo);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(todo));

        MvcResult mvcResult = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Todo todoreturn = mapFromJson(content, Todo.class);
        assertEquals("AD23E5R98EFT3SL00", todoreturn.getUuid());
        assertEquals("Go for a Walk!", todoreturn.getTaskName());
        assertEquals(content,this.mapper.writeValueAsString(todoreturn));
    }

    @Test
    public void delete_deleteTodos() throws Exception {
        String uuid = "AD23E5R98EFT3SL00";

        TodoService serviceSpy = Mockito.spy(todoService);

        Mockito.doReturn("Deleted").when(serviceSpy).deleteTodo(uuid);

        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/AD23E5R98EFT3SL00")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        verify(todoService, times(1)).deleteTodo(uuid);
    }

}
