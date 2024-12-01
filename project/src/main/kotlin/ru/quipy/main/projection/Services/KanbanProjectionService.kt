package ru.quipy.main.projection.Services

import org.springframework.stereotype.Service
import ru.quipy.main.api.AggregateEvents.StatusCreatedEvent
import ru.quipy.main.api.AggregateEvents.StatusDeletedEvent
import ru.quipy.main.projection.Projections.KanbanProjection
import ru.quipy.main.projection.Repositories.KanbanProjectionRepository

@Service
class KanbanProjectionService(
    private val repository: KanbanProjectionRepository,
) {

    fun getAll(): List<KanbanProjection> = repository.findAll()

    fun addStatus(
        event: StatusCreatedEvent
    ) {
        repository.save(
            KanbanProjection(
                statusName = event.status.takeLast(event.status.length - 7),
                tasks = ArrayList()
            )
        )
    }

    fun deleteStatus(
        event: StatusDeletedEvent
    ) {
        repository.deleteById(event.statusName)
    }

    fun updateTaskStatus(
        taskTitle: String,
        statusName: String,
    ) {
        val curStatusProjection = repository.findAll().firstOrNull { projection ->
            projection.tasks.any { task -> task.name == taskTitle }
        }
        if (curStatusProjection != null) {
            repository.save(
                KanbanProjection(
                    statusName = curStatusProjection.statusName,
                    tasks = curStatusProjection.tasks.filter { task -> task.name != taskTitle }
                )
            )
            repository.save(
                KanbanProjection(
                    statusName = statusName,
                    tasks = arrayListOf(
                        KanbanProjection.Task(
                            name = taskTitle,
                        )
                    ),
                )
            )
        } else {
            repository.save(
                KanbanProjection(
                    statusName = statusName,
                    tasks = arrayListOf(
                        KanbanProjection.Task(
                            name = taskTitle,
                        )
                    ),
                )
            )
        }
    }
}
