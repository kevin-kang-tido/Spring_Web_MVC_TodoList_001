package co.istad.mcvhomework.controller;

import co.istad.mcvhomework.model.Todo;
import co.istad.mcvhomework.respository.TodoListRepository;
import co.istad.mcvhomework.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class TodoController {
    private static final TodoListRepository todoListRepository = new TodoListRepository();
    private final TodoService todoService;
    //catch all data from the that created
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("todoList",todoListRepository.getFromList());
        return "index";
    }
    // search task in url , and isDone as well
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String task , @RequestParam(required = false) Boolean isDone, Model model){
       return todoService.searchingService(task,isDone,model);
    }
    @GetMapping("/new")
    public String createShow(Model model){
        model.addAttribute("todo",new Todo());
        return "create";
    }
    @PostMapping("/new")
    public String create(@ModelAttribute Todo todo, Model model ){
        return todoService.createService(todo, model);
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
       var editedTodo=   todoListRepository.getFromList()
                .stream()
                .filter(u -> u.getToDoID().equals(id))
                .findFirst();
        model.addAttribute("todo", editedTodo);
        return "edit";
    }
    @PostMapping("/edit")
    public String editUser(@ModelAttribute Todo todo) {
        todoListRepository.getFromList()
                .stream()
                .filter(u -> u.getToDoID().equals(todo.getToDoID()))
                .findFirst()
                .ifPresent(u -> {
                    u.setToDoID(todo.getToDoID());
                    u.setTask(todo.getTask());
                    u.setDescription(todo.getDescription());
                });
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
//        System.out.println("This is the value fo the id : "+id);
        todoListRepository.getFromList().removeIf(u -> u.getToDoID().equals(id));
        return "redirect:/";
     }


}
