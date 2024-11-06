package org.example.Services;

import org.example.Entities.ChangeLog;
import org.example.Repositories.ChangeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChangeLogService {

    @Autowired
    private ChangeLogRepository changelogRepository;

    // Получение всех логов изменений
    public List<ChangeLog> getAllChangeLogs() {
        return changelogRepository.findAll();
    }

    // Получение лога изменений по ID
    public Optional<ChangeLog> getChangeLogById(int id) {
        return changelogRepository.findById(id);
    }

    // Добавление нового лога изменения
    public ChangeLog addChangeLog(ChangeLog changeLog) {
        return changelogRepository.save(changeLog);
    }

    // Обновление лога изменения
    public ChangeLog updateChangeLog(ChangeLog changeLog) {
        return changelogRepository.save(changeLog);
    }

    // Удаление лога изменения по ID
    public void deleteChangeLogById(int id) {
        changelogRepository.deleteById(id);
    }
}
