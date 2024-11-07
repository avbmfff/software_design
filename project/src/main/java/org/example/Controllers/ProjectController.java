package org.example.Controllers;

import org.example.DTO.ProjectDTO;
import org.example.Entities.Project;
import org.example.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Получение всех проектов
    @GetMapping("/getAll")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // Получение проекта по ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") int id) {
        Optional<Project> project = projectService.getProjectById(id);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Добавление нового проекта
    @PostMapping("/addProject")
    public ResponseEntity<Project> addProject(@RequestBody ProjectDTO projectDTO) {
        Project project = new Project(
                projectService.getAllProjects().size()+1,
                projectDTO.getAuthorId(),
                projectDTO.getTitle(),
                projectDTO.getDescription(),
                projectDTO.getStartDate(),
                projectDTO.getEndDate(),
                projectDTO.getTaskStatus());
        project.addUserToProject(projectDTO.getAuthorId());
        Project createdProject = projectService.addProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    // Обновление данных проекта
    @PutMapping("/updateProject/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") int id, @RequestBody Project project) {
        if (project.getId() != id) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Project updatedProject = projectService.updateProject(project);
        return ResponseEntity.ok(updatedProject);
    }

    // Удаление проекта
    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable("id") int id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/getUserProjects/{id}")
    public List<Project> getProjectsByUserId(@PathVariable("id") int userId) {
        List<Project> projects = new ArrayList<>();
        List<Project> allProjects = projectService.getAllProjects();

        if (allProjects != null) {
            allProjects.forEach(project -> {
                if (project.getWorkersIds() != null && Arrays.stream(project.getWorkersIds()).anyMatch(id -> id == userId)) {
                    projects.add(project);
                }
            });
        }
        return projects;
    }

}
