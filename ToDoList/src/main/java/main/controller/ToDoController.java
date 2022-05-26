package main.controller;

import main.model.ToDo;
import main.service.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//
//@RestController
//@RequestMapping("/todos")
//public class ToDoController {
//
//    private final ToDoService toDoService;
//
//    public ToDoController(ToDoService toDoService) {
//        this.toDoService = toDoService;
//    }
//
//    @GetMapping("/get/all")
//    public List<ToDo> getAllTodos() {
//       return toDoService.getAllTodos();
//    }
//
//    @GetMapping("/get/{id}")
//    public ToDo getTodoById(@PathVariable("id") Long id) {
//       return toDoService.getTodoById(id);
//    }
//
//    @PostMapping("/add/new")
//    public String addNewTodo(@RequestBody ToDo todo) {
//        toDoService.addTodoAndReturnID(todo);
//        return "Todo is created successfully";
//    }
//
//    @PostMapping("/add/all")
//    public String addAllTodos(@RequestBody List<ToDo> toDos) {
//        toDoService.addAllTodos(toDos);
//        return "All todos is added";
//    }
//
//    @PutMapping("/update/all")
//    public String updateAllTodos(@RequestBody List<ToDo> toDoList) {
//        toDoService.updateAllTodos(toDoList);
//        return "All updates is successfully";
//    }
//
//    @PutMapping("/update/{id}")
//    public String updateTodoById(@PathVariable("id") Long id, @RequestBody ToDo toDo) throws Exception {
//       toDoService.updateTodoById(id, toDo);
//       return id+" todo is updated";
//    }
//
//    @DeleteMapping("/delete/all")
//    public String deleteAllTodos() {
//        toDoService.deleteAllTodos();
//        return "All todos is deleted successfully";
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteTodoByID(@PathVariable("id") Long id) {
//         if (toDoService.deleteTodoById(id)) {
//             return "Todo with ID "+id+" deleted";
//         }
//         return "Todo not found";
//
//    }
//
//}
