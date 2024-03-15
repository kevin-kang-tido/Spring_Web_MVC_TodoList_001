package co.istad.mcvhomework.controller;

import co.istad.mcvhomework.model.Todo;
import co.istad.mcvhomework.respository.TodoListRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {
    private static final TodoListRepository todoListRepository = new TodoListRepository();
    //catch all data from the that created
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("todoList",todoListRepository.getFromList());
        return "index";
    }



}
