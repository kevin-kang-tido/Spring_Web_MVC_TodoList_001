package co.istad.mcvhomework.service;


import co.istad.mcvhomework.model.Todo;
import co.istad.mcvhomework.respository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final  TodoListRepository todoListRepository;

    public String createService( Todo todo, Model model){
        //        todo = new Todo();
        todo.setCreateAt(LocalDate.now());
        todo.setToDoID(todo.getToDoID());
        todo.setTask(todo.getTask());
        todo.setDescription(todo.getDescription());
        todo.setIsDone(false);

       var newList =  todoListRepository.getFromList();
       newList.add(todo);
        model.addAttribute("todoList", newList);
        return "index";
    }
    public String searchingService(String task, Boolean isDone, Model model){
        List<Todo> tasks = todoListRepository.getFromList();

        // Stream the tasks
        Stream<Todo> taskStream = tasks.stream();
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


}
