package org.example.Services;

import org.example.Entities.Project;
import org.example.Repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Получение всех проектов
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Получение проекта по ID
    public Optional<Project> getProjectById(int id) {
        return projectRepository.findById(id);
    }

    // Добавление нового проекта
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    // Обновление данных проекта
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    // Удаление проекта по ID
    public void deleteProjectById(int id) {
        projectRepository.deleteById(id);
    }
}
