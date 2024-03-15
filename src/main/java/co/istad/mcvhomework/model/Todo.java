package co.istad.mcvhomework.model;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private Integer toDoID;
    private  String task;
    private  String description ;
    private  Boolean isDone;
    private  String CreateAt;

}
