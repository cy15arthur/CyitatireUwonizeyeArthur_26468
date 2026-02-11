package auca.ac.rw.question5_taskManagement_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.question5_taskManagement_api.model.Task;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
  List<Task> tasks = new ArrayList<>();

       public TaskController() {
        tasks.add(new Task(1L, "Finish Assignment", "Complete Web Tech projects", false, "HIGH", "2026-02-15"));
        tasks.add(new Task(2L, "Grocery Shopping", "Buy milk and eggs", true, "LOW", "2026-02-10"));
        tasks.add(new Task(3L, "Gym Session", "Leg day workout", false, "MEDIUM", "2026-02-11"));
        tasks.add(new Task(4L, "Read Book", "Finish reading 'The Alchemist'", true, "LOW", "2026-02-20"));
        tasks.add(new Task(5L, "Pay Bills", "Electricity and Internet bills", false, "HIGH", "2026-02-12"));

 } 
    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }
    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) return t;
        }
        return null;
    }

    @GetMapping("/status")
    public List<Task> getTasksbyStatus(@RequestParam boolean completed) {
        List<Task> filteredtask = new ArrayList<>();
        for (Task temptask : tasks) {
            if (temptask.isCompleted() == completed) filteredtask.add(temptask);
        }
        return filteredtask;
        }
    @GetMapping("/priority/{priority}")
    public List<Task> getTasksbyPriority(@PathVariable String priority) {
        List<Task> filtered = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getPriority().equalsIgnoreCase(priority)) filtered.add(t);
        }
        return filtered;
       }

    @PostMapping
    public Task createtask(@RequestBody Task newTask) {
        tasks.add(newTask);
        return newTask;
      }

      @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task updatedInfo) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                t.setTitle(updatedInfo.getTitle());
                t.setDescription(updatedInfo.getDescription());
                t.setCompleted(updatedInfo.isCompleted());
                t.setPriority(updatedInfo.getPriority());
                t.setDueDate(updatedInfo.getDueDate());
                return t;
            }
        }
        return null;
    }

    @PatchMapping("/{taskId}/complete")
    public Task markascompleted(@PathVariable Long taskId) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                t.setCompleted(true);
                return t;
            }
        }
        return null;
    }

    @DeleteMapping("/{taskId}")
    public String deletetask(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                tasks.remove(task);
                return "Task deleted";
            }
        }
        return "Task not found";
    }
}    
