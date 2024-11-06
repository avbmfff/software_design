package org.example.Controllers;

import org.example.Entities.Project;
import org.example.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Получение всех проектов
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // Получение проекта по ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) {
        Optional<Project> project = projectService.getProjectById(id);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Добавление нового проекта
    @PostMapping
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        Project createdProject = projectService.addProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    // Обновление данных проекта
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable int id, @RequestBody Project project) {
        if (project.getId() != id) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Project updatedProject = projectService.updateProject(project);
        return ResponseEntity.ok(updatedProject);
    }

    // Удаление проекта
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable int id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
