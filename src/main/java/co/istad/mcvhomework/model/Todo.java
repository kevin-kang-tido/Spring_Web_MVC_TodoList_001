package co.istad.mcvhomework.model;

import lombok.*;
import org.apache.el.stream.Stream;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private Integer toDoID;
    private  String task;
    private  String description ;
    private  Boolean isDone;
    private LocalDate createAt;

}
