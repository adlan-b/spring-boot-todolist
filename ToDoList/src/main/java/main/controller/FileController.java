package main.controller;


import main.model.ToDo;
import main.service.FileService;
import main.service.ToDoServiceIml;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileController {

    private final FileService fileService;
    private final ToDoServiceIml toDoServiceIml;

    public FileController(FileService fileService, ToDoServiceIml toDoServiceIml) {
        this.fileService = fileService;
        this.toDoServiceIml = toDoServiceIml;
    }

    @GetMapping("/upload/{id}")
    public String index(@PathVariable("id")Long id, Model model) {
        ToDo toDo = toDoServiceIml.getTodoById(id);
        model.addAttribute("toDo", toDo);
        return "upload";
    }

    @PostMapping(value = "/uploadFile/{id}", produces = { "application/json" },
            consumes = {"multipart/form-data"})
    public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        ToDo t = toDoServiceIml.getTodoById(id);
        fileService.addImage(file, t);
        return "redirect:/";
    }
}
