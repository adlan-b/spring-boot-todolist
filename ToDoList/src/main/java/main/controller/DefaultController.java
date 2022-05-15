package main.controller;

import main.model.ToDo;
import main.service.ToDoService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DefaultController {

    private final ToDoService service;

    public DefaultController(ToDoService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public String greeting(Model model) {
        List<ToDo> todos = service.getAllTodos();
        model.addAttribute("todos", todos);
        return "index";
    }

    @PostMapping(value = "/")
    public String addTodo(ToDo toDo) {
        service.addTodoAndReturnID(toDo);
        return "redirect:/";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteTodoById(@PathVariable("id") Long id) {
        service.deleteTodoById(id);
        return "redirect:/";
    }

    @Transactional
    @PostMapping ("/completed/{id}")
    public String completedToDo(@PathVariable Long id) {
        ToDo toDo = service.getTodoById(id);
        toDo.setCompleted(true);
        return "redirect:/";
    }

}
