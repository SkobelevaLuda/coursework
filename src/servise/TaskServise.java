package servise;

import exception.TaskNotFoundException;
import task.Task1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

public class TaskServise {
    private static final Map<Integer, Task1> TASKS = new HashMap<>();
    private TaskServise(){

    }
    public static void add (Task1 task1){
        TASKS.put(task1.getId(),task1);
    }
    public static Collection<Task1> getTaskByDay(LocalDate localDate){
        Collection <Task1> taskByDay = new ArrayList<>();
        Collection<Task1> allTasks = TASKS.values();
        for (Task1 task1: allTasks){
            LocalDateTime currentDateTame= task1.getDateTime();
            LocalDateTime nextDateTime= task1.getRepiatability().nexTime(currentDateTame);
            ChronoLocalDate day = null;
            if (nextDateTime==null || currentDateTame.toLocalDate().equals(day)){
                taskByDay.add(task1);
                continue;
            }
            do {
                if(nextDateTime.toLocalDate().equals(day)){
                    taskByDay.add(task1);
                    break;
                }
                nextDateTime= task1.getRepiatability().nexTime(nextDateTime);
            }while (nextDateTime.toLocalDate().isBefore(day));
        }

        return taskByDay;


    }
    public static void removeById (int id) throws TaskNotFoundException {
        if (TASKS.remove(id)==null){
            throw new TaskNotFoundException(id);
        }
    }
}
