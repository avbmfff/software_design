package ru.quipy.main.logic.AggregateStates

import org.testcontainers.shaded.com.google.common.annotations.VisibleForTesting
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import ru.quipy.main.api.AggregateEvents.*
import ru.quipy.main.api.Aggregates.ProjectAggregate
import ru.quipy.main.api.DataClasses.ProjectEntity
import ru.quipy.main.api.DataClasses.StatusEntity
import ru.quipy.main.api.DataClasses.TaskEntity
import java.util.*
import kotlin.collections.ArrayList

class ProjectAggregateState : AggregateState<Int, ProjectAggregate> {

    @VisibleForTesting
    @Volatile
    var project: ProjectEntity? = null

    override fun getId(): Int? = project?.projectId

    fun createProject(
        projectId: Int,
        projectTitle: String,
        projectDescription: String,
        authorId: Int,
        startDate: Date,
        endDate: Date,
    ): ProjectCreatedEvent {
        return ProjectCreatedEvent(
            projectId = projectId,
            projectTitle = projectTitle,
            projectDescription = projectDescription,
            authorId = authorId,
            startDate = startDate,
            endDate = endDate,
        )
    }

    fun addWorkerToProject(
        projectId: Int,
        workerId: Int,
        workersIds: ArrayList<Int>,
    ): WorkerAddedToProjectEvent {
        if (project?.workersIds?.any{ curId -> curId == workerId } != null) {
            throw IllegalStateException("Project already containing this user")
        }
        workersIds.add(workerId)
        return WorkerAddedToProjectEvent(
            projectId = projectId,
            workerId = workerId,
            wokrersIds = workersIds,
        )
    }

    fun createTask(
        projectId: Int,
        taskTitle: String,
        taskDescription: String,
        taskStatus: String,
        authorId: Int,
        dedicatedTime: Date,
        workersIds: ArrayList<Int>,
    ): TaskCreatedEvent {
        val statusColor = taskStatus.take(7)
        val statusName = taskStatus.takeLast(taskStatus.length - 7)
        val taskStatusEntity = StatusEntity(statusColor,statusName)
        val task = TaskEntity(taskTitle,taskDescription,taskStatusEntity,authorId,dedicatedTime,workersIds)
        if (project?.tasks?.contains(task) == true) {
            throw IllegalStateException("The task is already in project")
        }
        return TaskCreatedEvent(
            projectId = projectId,
            taskTitle = taskTitle,
            taskDescription = taskDescription,
            taskAuthorId = authorId,
            taskDedicatedTime = dedicatedTime,
        )
    }

    fun addWorkerToTask(
        projectId: Int,
        taskTitle: String,
        workerId: Int,
        workersIds: ArrayList<Int>,
    ): WorkerAssignedToTaskEvent {
        val curTask = project?.tasks?.find { task -> task.title == taskTitle }
        if(curTask == null ){
            throw IllegalStateException("There is no such task in project")
        }
        if(curTask.workers.contains(workerId)){
            throw IllegalStateException("User already working on this task")
        }

        workersIds.add(workerId)
        return WorkerAssignedToTaskEvent(
            projectId = projectId,
            taskTitle = taskTitle,
            workerId = workerId,
        )
    }

    fun updateTask(
        projectId: Int,
        taskTitle: String,
        taskDescription: String,
        taskStatus: String,
    ): TaskUpdatedEvent {
        val statusColor = taskStatus.take(7)
        val statusName = taskStatus.takeLast(taskStatus.length - 7)
        val curStatus = StatusEntity(statusColor,statusName)
        if (project?.projectId == null){
            throw IllegalStateException("The project is null")
        }
        return TaskUpdatedEvent(
            projectId = projectId,
            taskTitle = taskTitle,
            taskDescription = taskDescription,
            taskStatus = curStatus,
        )
    }

    fun deleteTask(
        projectId: Int,
        taskTitle: String,
    ): TaskDeletedEvent {
        if (project?.projectId == null){
            throw IllegalStateException("The project is null")
        }

        val curTask = project?.tasks?.find { curTask -> curTask.title == taskTitle }
        if(curTask == null){
            throw IllegalStateException("There is no such task in project")
        }

        project?.tasks?.minus(curTask)

        return TaskDeletedEvent(
            projectId = projectId,
            taskTitle = taskTitle,
        )
    }

    fun createStatus(
        projectId: Int,
        status: String,
    ): StatusCreatedEvent {
        val statusColor = status.take(7)
        val statusName = status.takeLast(status.length - 7)
        val taskStatusEntity = StatusEntity(statusColor,statusName)

        if (project?.projectId == null){
            throw IllegalStateException("The project is null")
        }
        if (project?.tasksStatuses?.contains(taskStatusEntity) == true) {
            throw IllegalStateException("The task is already in project")
        }

        project?.tasksStatuses?.plus(taskStatusEntity)

        return StatusCreatedEvent(
            projectId = projectId,
            status = status,
        )
    }

    fun deleteStatus(
        projectId: Int,
        statusName: String,
    ): StatusDeletedEvent {
        if(project?.projectId == null){
            throw IllegalStateException("The project is null")
        }

        if(project?.tasksStatuses?.find { curStatus -> curStatus.name == statusName } == null){
            throw IllegalStateException("The status you want to delete does not exist")
        }

        project?.tasksStatuses?.minus(project?.tasksStatuses?.find{ curStatus -> curStatus.name == statusName })

        return StatusDeletedEvent(
            projectId = projectId,
            statusName = statusName,
        )
    }


    @StateTransitionFunc
    fun projectCreatedEventTransition(event: ProjectCreatedEvent) {

        project = ProjectEntity(
            projectId = event.projectId,
            projectTitle = event.projectTitle,
            projectDescription = event.projectDescription,
            authorId = event.authorId,
            startDate = event.startDate,
            endDate = event.endDate,
        )
    }

    @StateTransitionFunc
    fun workerAddedToProjectEventTransition(event: WorkerAddedToProjectEvent) {
        project = project?.copy(
            workersIds = (project?.workersIds?.plus(event.workerId)) as ArrayList<Int>,
        )
    }

    @StateTransitionFunc
    fun workerAssignedToTaskEventTransition(event: WorkerAssignedToTaskEvent) {
        project?.tasks?.find { task -> task.title == event.taskTitle}?.workers?.plus(event.workerId)
    }

    @StateTransitionFunc
    fun taskCreatedEventTransition(event: TaskCreatedEvent) {
        val project = project!!

        val curTask = TaskEntity(
            title = event.taskTitle,
            description = event.taskDescription,
            status = StatusEntity(),
            authorId = event.taskAuthorId,
            dedicatedTime = event.taskDedicatedTime,
            workers = ArrayList()
        )

        this.project = project.copy(
            tasks = project.tasks.plus(curTask),
        )

    }

    @StateTransitionFunc
    fun taskUpdatedEventTransition(event: TaskUpdatedEvent) {
        val project = project!!
        val oldTask = project.tasks.find { task -> task.title == event.taskTitle }!!
        val curTask = oldTask.copy(
            description = event.taskDescription,
            status = event.taskStatus,
        )

        this.project = project.copy(tasks = project.tasks.minus(oldTask).plus(curTask))
    }

    @StateTransitionFunc
    fun taskDeletedEventTransition(event: TaskDeletedEvent) {
        val project = project!!
        val curTask = project.tasks.find { task -> task.title == event.taskTitle }!!
        this.project = project.copy(
            tasks = project.tasks.minus(curTask),
        )
    }

    @StateTransitionFunc
    fun statusCreatedEventTransition(event: StatusCreatedEvent) {
        val project = project!!
        val statusColor = event.status.take(7)
        val statusName = event.status.takeLast(event.status.length - 7)
        val taskStatusEntity = StatusEntity(statusColor,statusName)
        this.project = project.copy(
            tasksStatuses = project.tasksStatuses.plus(taskStatusEntity),
        )
    }

    @StateTransitionFunc
    fun statusDeletedEventTransition(event: StatusDeletedEvent) {
        val project = project!!
        val curStatus = project.tasksStatuses.find { status -> status.name == event.statusName }!!
        this.project = project.copy(
            tasksStatuses = project.tasksStatuses.minus(curStatus),
        )
    }
}
