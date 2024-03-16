package co.istad.mcvhomework.controller;

import co.istad.mcvhomework.model.Todo;
import co.istad.mcvhomework.respository.TodoListRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class TodoController {

    private static final TodoListRepository todoListRepository = new TodoListRepository();
    //catch all data from the that created
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("todoList",todoListRepository.getFromList());
        return "index";
    }
    // search task in url , and isDone as well
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String task , @RequestParam(required = false) Boolean isDone, Model model){
        List<Todo> tasks = todoListRepository.getFromList();

        // Stream the tasks
        Stream<Todo
                > taskStream = tasks.stream();

        // Apply the task filter if the task parameter is present
        if (task != null && !task.isEmpty()) {
            taskStream = taskStream.filter(e -> e.getTask().contains(task));
        }

        // Apply the isDone filter if the isDone parameter is present
        if (isDone != null) {
            taskStream = taskStream.filter(e -> e.getIsDone() == isDone);
        }

        // Collect the filtered tasks into a list
        List<Todo> searchTasks = taskStream.collect(Collectors.toList());

        // Add the filtered tasks to the model
        model.addAttribute("todoList", searchTasks);


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
        todoListRepository.getFromList().stream()
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
