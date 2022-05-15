package main.service;

import main.exception.ToDoNotFoundException;
import main.model.ToDo;
import main.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceIml implements ToDoService {
    private final ToDoRepository repository;

    @Autowired
    public ToDoServiceIml(ToDoRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<ToDo> getAllTodos() {
        List<ToDo> allTodos = new ArrayList<>();
        Iterable<ToDo> iterTodos = repository.findAll();
        iterTodos.forEach(allTodos::add);
        return allTodos;
    }

    @Override
    public ToDo getTodoById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ToDoNotFoundException("No such todo by id " + id));
    }

    @Override
    public Long addTodoAndReturnID(ToDo todo) {
        ToDo toDo = repository.save(todo);
        return toDo.getId();
    }

    @Override
    public List<ToDo> addAllTodos(List<ToDo> todos) {
        List<ToDo> newList = new ArrayList<>();
        for (ToDo t : todos) {
            if (t != null) {
                newList.add(t);
            }
        }
        repository.saveAll(newList);
        return newList;
    }

    @Override
    public List<ToDo> updateAllTodos(List<ToDo> toDoList) {
        List<ToDo> toDos = new ArrayList<>();
        for (ToDo todo : toDoList) {
            if (repository.findById(todo.getId()).isPresent()) {
                ToDo toDo = repository.findById(todo.getId()).get();
                toDo.setName(todo.getName());
                toDo.setDescription(todo.getDescription());
                toDo.setCompleted(todo.isCompleted());
                toDos.add(toDo);
            }
        }
        repository.saveAll(toDos);
        return toDos;
    }

    @Override
    public ToDo updateTodoById(Long id, ToDo toDo1) {
        Optional<ToDo> toDo = repository.findById(id);
        if (toDo.isPresent()) {
            repository.save(toDo1);
            return toDo1;
        }
        throw new ToDoNotFoundException("No such todo by id " + id);

    }

    @Override
    public void deleteAllTodos() {
        repository.deleteAll();
    }

    @Override
    public boolean deleteTodoById(Long id) {
        Optional<ToDo> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.delete(optional.get());
            return true;
        }
        throw new ToDoNotFoundException("Todo not found for deleting");
    }
}
