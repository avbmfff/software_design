package org.example.Controllers;

import org.example.Entities.Task;
import org.example.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Получение всех задач
    @GetMapping("/getAll")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Получение задачи по ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") int id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Добавление новой задачи
    @PostMapping("/addTask")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task createdTask = taskService.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    // Обновление данных задачи
    @PutMapping("/updateTask/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") int id, @RequestBody Task task) {
        if (task.getId() != id) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Task updatedTask = taskService.updateTask(task);
        return ResponseEntity.ok(updatedTask);
    }

    // Удаление задачи
    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable("id") int id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
