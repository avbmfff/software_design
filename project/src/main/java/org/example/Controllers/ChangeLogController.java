package org.example.Controllers;

import org.example.Entities.ChangeLog;
import org.example.Services.ChangeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/changelogs")
public class ChangeLogController {

    @Autowired
    private ChangeLogService changeLogService;

    // Получение всех логов изменений
    @GetMapping
    public List<ChangeLog> getAllChangeLogs() {
        return changeLogService.getAllChangeLogs();
    }

    // Получение лога изменений по ID
    @GetMapping("/{id}")
    public ResponseEntity<ChangeLog> getChangeLogById(@PathVariable int id) {
        Optional<ChangeLog> changeLog = changeLogService.getChangeLogById(id);
        return changeLog.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Добавление нового лога изменения
    @PostMapping
    public ResponseEntity<ChangeLog> addChangeLog(@RequestBody ChangeLog changeLog) {
        ChangeLog createdChangeLog = changeLogService.addChangeLog(changeLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChangeLog);
    }

    // Обновление лога изменения
    @PutMapping("/{id}")
    public ResponseEntity<ChangeLog> updateChangeLog(@PathVariable int id, @RequestBody ChangeLog changeLog) {
        if (changeLog.getId() != id) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        ChangeLog updatedChangeLog = changeLogService.updateChangeLog(changeLog);
        return ResponseEntity.ok(updatedChangeLog);
    }

    // Удаление лога изменения
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChangeLogById(@PathVariable int id) {
        changeLogService.deleteChangeLogById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
