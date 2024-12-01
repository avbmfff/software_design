package ru.quipy.main.projection

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.quipy.main.api.AggregateEvents.*
import ru.quipy.streams.annotation.AggregateSubscriber
import ru.quipy.streams.annotation.SubscribeEvent
import ru.quipy.main.api.Aggregates.ProjectAggregate
import ru.quipy.main.api.DataClasses.StatusEntity
import ru.quipy.main.projection.Services.KanbanProjectionService
import ru.quipy.main.projection.Services.TasksProjectionService
import ru.quipy.main.projection.Services.UserProjectionService

@Service
@AggregateSubscriber(
    aggregateClass = ProjectAggregate::class, subscriberName = "project-aggregate-subscriber"
)
class ProjectAggregateSubscriber {

    @Autowired
    lateinit var userProjectionService: UserProjectionService

    @Autowired
    lateinit var tasksProjectionService: TasksProjectionService

    @Autowired
    lateinit var kanbanProjectionService: KanbanProjectionService

    @SubscribeEvent
    fun onWorkerAddedToProject(event: WorkerAddedToProjectEvent) {
        event.wokrersIds.forEach { workerId ->
            userProjectionService.addProject(
                id = event.workerId,
                projectId = event.projectId,
            )
        }
    }

    @SubscribeEvent
    fun onTaskCreated(event: TaskCreatedEvent) {
        tasksProjectionService.addTask(event)
        kanbanProjectionService.updateTaskStatus(
            taskTitle = event.taskTitle,
            statusName = StatusEntity().name,
        )
    }

    @SubscribeEvent
    fun onTaskUpdated(event: TaskUpdatedEvent) {
        tasksProjectionService.updateTask(event)
        kanbanProjectionService.updateTaskStatus(
            taskTitle = event.taskTitle,
            statusName = event.taskStatus.name,
        )
    }

    @SubscribeEvent
    fun onTaskDeleted(event: TaskDeletedEvent) {
        tasksProjectionService.deleteTask(event)
    }

    @SubscribeEvent
    fun onStatusDeleted(event: StatusDeletedEvent) {
        kanbanProjectionService.deleteStatus(event)
    }

    @SubscribeEvent
    fun onStatusCreated(event: StatusCreatedEvent) {
        kanbanProjectionService.addStatus(event)
    }
}
