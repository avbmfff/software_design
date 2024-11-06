package org.example.Repositories;

import org.example.Entities.ChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeLogRepository extends JpaRepository<ChangeLog, Integer> {
    // Методы для поиска записей лога по полям, если нужно
}
