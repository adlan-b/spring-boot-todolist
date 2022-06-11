package main.service;

import main.model.ToDo;

import java.util.List;

public interface ToDoService {
    List<ToDo> getAllTodos();
    ToDo getTodoById(Long id);
    List<ToDo> addAllTodos(List<ToDo> todos);
    Long addTodoAndReturnID(ToDo todo);
    List<ToDo> updateAllTodos(List<ToDo> updates);
    ToDo updateTodoById(Long id, ToDo todo);
    void deleteAllTodos();
    boolean deleteTodoById(Long id);
    List<ToDo> getAllTodosByStatus(boolean status);
}
