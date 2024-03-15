package co.istad.mcvhomework.respository;

import co.istad.mcvhomework.model.Todo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoListRepository {
    // Get the current date
    LocalDate currentDate = LocalDate.now();

    // Format the current date as "dd/MM/yyyy"
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String formattedDate = currentDate.format(formatter);
    private List<Todo> todoList = new ArrayList<>();
    public TodoListRepository(){
        todoList.add(new Todo(1,"watch spring video ","video two",true,formattedDate));
        todoList.add(new Todo(2,"watch spring video ","video two",true,formattedDate));
        todoList.add(new Todo(3,"watch java video ","video two",true,formattedDate));
        todoList.add(new Todo(4,"watch web video ","video two",true,formattedDate));
        todoList.add(new Todo(5,"watch another video ","video two",true,formattedDate));
    }
    public List<Todo> getFromList(){
        return todoList;
    }

}
