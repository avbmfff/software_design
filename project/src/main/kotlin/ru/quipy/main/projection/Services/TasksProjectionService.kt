package ru.quipy.main.projection.Services

import org.springframework.stereotype.Service
import ru.quipy.main.api.AggregateEvents.TaskCreatedEvent
import ru.quipy.main.api.AggregateEvents.TaskDeletedEvent
import ru.quipy.main.api.AggregateEvents.TaskUpdatedEvent
import ru.quipy.main.projection.Projections.TasksProjection
import ru.quipy.main.projection.Repositories.TasksProjectionRepository
import java.util.*
import kotlin.collections.ArrayList

@Service
class TasksProjectionService(
    private val repository: TasksProjectionRepository,
) {

    fun getAll(): List<TasksProjection> = repository.findAll()

    fun addTask(
        event: TaskCreatedEvent
    ) {
        val curProject = repository.existsById(event.projectId)
        val curProjection = when (curProject) {
            true -> {
                val projection = repository.findById(event.projectId).get()
                projection.copy(
                    tasks = projection.tasks.plus(
                        taskGen(
                            projectId = event.projectId,
                            taskTitle = event.taskTitle,
                            taskDescription = event.taskDescription,
                            taskAuthorId = event.taskAuthorId,
                            taskDedicatedTime = event.taskDedicatedTime,
                        )
                    )
                )
            }

            false -> TasksProjection(
                projectId = event.projectId,
                tasks = arrayListOf(
                    taskGen(
                        projectId = event.projectId,
                        taskTitle = event.taskTitle,
                        taskDescription = event.taskDescription,
                        taskAuthorId = event.taskAuthorId,
                        taskDedicatedTime = event.taskDedicatedTime,
                    )
                ),
            )
        }
        repository.save(curProjection)
    }

    fun updateTask(
        event: TaskUpdatedEvent
    ) {
        val projection = repository.findById(event.projectId).get()
        val curProjection = repository.save(
            projection.copy(
                tasks = projection.tasks.map { task ->
                    when (task.taskTitle) {
                        event.taskTitle -> task.copy(
                            taskDescription = event.taskDescription,
                            taskStatus = event.taskStatus.name,
                        )

                        else -> task
                    }
                }.toList(),
            )
        )
        repository.save(curProjection)
    }

    fun deleteTask(
        event: TaskDeletedEvent
    ) {
        val projection = repository.findById(event.projectId).get()
        val curProjection = repository.save(
            projection.copy(
                tasks = projection.tasks.filter { task -> task.taskTitle != event.taskTitle }.toList()
            )
        )
        repository.save(curProjection)
    }

    private fun taskGen(
        projectId: Int,
        taskTitle: String,
        taskDescription: String,
        taskAuthorId: Int,
        taskDedicatedTime: Date,
    ) = TasksProjection.Task(
        projectId = projectId,
        taskTitle = taskTitle,
        taskDescription = taskDescription,
        authorId = taskAuthorId,
        taskStatus = "#000000CREATED",
        dedicatedTime = taskDedicatedTime,
        workersIds = ArrayList()
    )
}
