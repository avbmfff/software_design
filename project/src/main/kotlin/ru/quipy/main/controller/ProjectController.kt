package ru.quipy.main.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.core.EventSourcingService
import ru.quipy.main.api.AggregateEvents.*
import ru.quipy.main.api.Aggregates.ProjectAggregate
import ru.quipy.main.api.DataClasses.*
import ru.quipy.main.logic.AggregateStates.ProjectAggregateState
import ru.quipy.main.projection.Services.TasksProjectionService

@RestController
@RequestMapping("/project")
class ProjectController(
    private val projectService: EventSourcingService<Int, ProjectAggregate, ProjectAggregateState>,
    private val projectTasksProjectionService: TasksProjectionService,
) {

    @PostMapping("/GetAllTasks")
    fun getAllTasksGroupedByProject() = projectTasksProjectionService.getAll()

    @PostMapping("/CreateProject")
    fun createProject(@RequestBody body: ProjectCreationBody): ProjectCreatedEvent {
        return projectService.create { state ->
            state.createProject(
                projectId = body.projectId,
                projectTitle = body.projectTitle,
                projectDescription = body.projectDescription,
                authorId = body.authorId,
                startDate = body.startDate,
                endDate = body.endDate,
            )
        }
    }



    @PostMapping("/AddWorkerToProject")
    fun addWorker(
        @RequestBody body: ProjectWorkerBody,
    ): WorkerAddedToProjectEvent {
        return projectService.update(
            aggregateId = body.projectId,
        ) { state ->
            state.addWorkerToProject(
                projectId = body.projectId,
                workerId = body.workerId,
                workersIds = body.workersIds,
            )
        }
    }

    @PostMapping("/CreateTask")
    fun createTask(
        @RequestBody body: TaskCreationBody
    ): TaskCreatedEvent {
        return projectService.update(
            aggregateId = body.projectId,
        ) { state ->
            state.createTask(
                projectId = body.projectId,
                taskTitle = body.taskTitle,
                taskDescription = body.taskDescription,
                taskStatus = body.taskStatus,
                authorId = body.authorId,
                dedicatedTime = body.dedicatedTime,
                workersIds = ArrayList()
            )
        }
    }



    @PutMapping("/UpdateTask")
    fun updateTask(
        @RequestBody body: TaskUpdatingBody
    ): TaskUpdatedEvent {
        return projectService.update(
            aggregateId = body.projectId,
        ) { state ->
            state.updateTask(
                projectId = body.projectId,
                taskTitle = body.taskTitle,
                taskDescription = body.taskDescription,
                taskStatus = body.taskStatus,
            )
        }
    }



    @DeleteMapping("/DeleteTask")
    fun deleteTask(
        @RequestBody body: TaskDeletionBody
    ): TaskDeletedEvent {
        return projectService.update(
            aggregateId = body.projectId,
        ) { state ->
            state.deleteTask(
                projectId = body.projectId,
                taskTitle = body.taskTitle,
            )
        }
    }



    @PostMapping("/CreateStatus")
    fun createStatus(
        @RequestBody body: StatusCreationBody,
    ): StatusCreatedEvent {
        return projectService.update(
            aggregateId = body.projectId,
        ) { state ->
            state.createStatus(
                projectId = body.projectId,
                status = body.status,
            )
        }
    }

    @DeleteMapping("/DeleteStatus")
    fun deleteOrderStatus(
        @RequestBody body: StatusDeletionBody
    ): StatusDeletedEvent {
        return projectService.update(
            aggregateId = body.projectId,
        ) { state ->
            state.deleteStatus(
                projectId = body.projectId,
                statusName = body.statusName,
            )
        }
    }
}
