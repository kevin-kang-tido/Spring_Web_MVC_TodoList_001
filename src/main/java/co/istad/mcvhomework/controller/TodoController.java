package co.istad.mcvhomework.controller;

import co.istad.mcvhomework.model.Todo;
import co.istad.mcvhomework.respository.TodoListRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class TodoController {

    private static final TodoListRepository todoListRepository = new TodoListRepository();
    //catch all data from the that created
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("todoList",todoListRepository.getFromList());
        return "index";
    }
    @GetMapping("/new")
    public String createShow(Model model){
        model.addAttribute("todo",new Todo());
        return "create";
    }
    @PostMapping("/new")
    public String create(@ModelAttribute Todo todo){
//        todo = new Todo();
        todo.setCreateAt(LocalDate.now());
        todo.setToDoID(todo.getToDoID());
        todo.setTask(todo.getTask());
        todo.setDescription(todo.getDescription());
        todo.setIsDone(false);
        todoListRepository.getFromList().add(todo);
        return "redirect:/";
    }





}
