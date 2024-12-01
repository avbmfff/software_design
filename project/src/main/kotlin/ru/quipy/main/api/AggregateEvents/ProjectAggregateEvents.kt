package ru.quipy.main.api.AggregateEvents

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import ru.quipy.main.api.Aggregates.ProjectAggregate
import ru.quipy.main.api.DataClasses.StatusEntity
import java.util.*

private const val PROJECT_CREATED_EVENT = "PROJECT_CREATED_EVENT"
private const val WORKER_ADDED_TO_PROJECT_EVENT = "WORKER_ADDED_TO_PROJECT_EVENT"
private const val TASK_CREATED_EVENT = "TASK_CREATED_EVENT"
private const val TASK_UPDATED_EVENT = "TASK_UPDATED_EVENT"
private const val WORKER_ASSIGNED_TO_TASK_EVENT = "WORKER_ASSIGNED_TO_TASK_EVENT"
private const val TASK_DELETED_EVENT = "TASK_DELETED_EVENT"
private const val STATUS_CREATED_EVENT = "STATUS_CREATED_EVENT"
private const val STATUS_DELETED_EVENT = "STATUS_DELETED_EVENT"

@DomainEvent(name = PROJECT_CREATED_EVENT)
class ProjectCreatedEvent(
    val projectId: Int,
    val projectTitle: String,
    val projectDescription: String,
    val authorId : Int,
    val startDate: Date,
    val endDate: Date,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = PROJECT_CREATED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = WORKER_ADDED_TO_PROJECT_EVENT)
class WorkerAddedToProjectEvent(
    val projectId: Int,
    val workerId: Int,
    val wokrersIds: ArrayList<Int>,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = WORKER_ADDED_TO_PROJECT_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = TASK_CREATED_EVENT)
class TaskCreatedEvent(
    val projectId: Int,
    val taskTitle: String,
    val taskDescription: String,
    val taskAuthorId: Int,
    val taskDedicatedTime: Date,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = TASK_CREATED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = TASK_UPDATED_EVENT)
class TaskUpdatedEvent(
    val projectId: Int,
    val taskTitle: String,
    val taskDescription: String,
    val taskStatus: StatusEntity,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = TASK_UPDATED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = WORKER_ASSIGNED_TO_TASK_EVENT)
class WorkerAssignedToTaskEvent(
    val projectId: Int,
    val taskTitle: String,
    val workerId: Int,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = WORKER_ASSIGNED_TO_TASK_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = TASK_DELETED_EVENT)
class TaskDeletedEvent(
    val projectId: Int,
    val taskTitle: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = TASK_DELETED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = STATUS_CREATED_EVENT)
class StatusCreatedEvent(
    val projectId: Int,
    val status: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = STATUS_CREATED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = STATUS_DELETED_EVENT)
class StatusDeletedEvent(
    val projectId: Int,
    val statusName: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = STATUS_DELETED_EVENT,
    createdAt = createdAt,
)
