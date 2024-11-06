package org.example.Services;

import org.example.Entities.Task;
import org.example.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Получение всех задач
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Получение задачи по ID
    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    // Добавление новой задачи
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    // Обновление данных задачи
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    // Удаление задачи по ID
    public void deleteTaskById(int id) {
        taskRepository.deleteById(id);
    }
}
