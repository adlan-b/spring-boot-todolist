package main.controller;

//import org.junit.jupiter.api.Test;

import main.model.ToDo;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ToDoControllerTest extends AbstractIntegrationTest {

    @Test
    public void getTodoListTest() throws Exception {
        ToDo toDo = new ToDo(1L, "Title", "Case Test");
        List<ToDo> todos = new ArrayList<>(Arrays.asList(toDo));

        when(toDoService.getAllTodos()).thenReturn(todos);

        mvc.perform(get(url + "/get/all")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getTodoTest() throws Exception {
        ToDo toDo1 = new ToDo(1L, "todo1", "First case");
        toDo1.setCompleted(true);

        given(toDoService.addTodoAndReturnID(toDo1)).willReturn(1L);

        String input = mapper.writeValueAsString(toDo1);
        mvc.perform(get(url + "/get/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(input))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void addTodoTest() throws Exception {
        ToDo todo = new ToDo(1L, "Test Title", "Method ADD Todo Test");
        todo.setCompleted(true);
        String input = mapper.writeValueAsString(todo);
        mvc.perform(post(url + "/add/new")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(input))
                .andExpect(status().isOk())
                .andExpect(content().string("Todo is created successfully"));

    }

    @Test
    public void updateAllTodoTest() throws Exception {
        ToDo toDo = new ToDo(1L, "Title", "Case Test");
        ToDo toDo2 = new ToDo(2L, "todo2", "Second case");
        toDo2.setCompleted(true);

        List<ToDo> todos = Arrays.asList(toDo, toDo2);
        String input = mapper.writeValueAsString(todos);

        mvc.perform(put(url + "/update/all")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(input))
                .andExpect(status().isOk())
                .andExpect(content().string("All updates is successfully"));
    }

    @Test
    public void updateTodoTest() throws Exception {
        ToDo toDo2 = new ToDo(2L, "todo2", "Second case");
        toDo2.setName("Todo");
        String input = mapper.writeValueAsString(toDo2);
        when(toDoService.updateTodoById(1L, toDo2)).thenReturn(toDo2);
        mvc.perform(put(url + "/update/{id}", toDo2.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(input))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(toDo2.getId() + " todo is updated"));

    }

    @Test
    public void deleteTodoTest() throws Exception {
        ToDo toDo = new ToDo(1L, "todo3", "Third case");
        toDo.setCompleted(true);
        when(toDoService.deleteTodoById(1L)).thenReturn(true);
        mvc.perform(delete(url + "/delete/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("Todo with ID " + toDo.getId() + " deleted"));
    }


    @Test
    public void deleteAllTodoTest() throws Exception {
        mvc.perform(delete(url + "/delete/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("All todos is deleted successfully"));

    }

    @Test
    public void updateNonExistentTodoTest() throws Exception {
        mvc.perform(put(url + "/update/{id}", -1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void deleteNonExistentTodoTest() throws Exception {
        mvc.perform(delete(url + "/delete/{id}", -1L)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Todo not found"));

    }

}