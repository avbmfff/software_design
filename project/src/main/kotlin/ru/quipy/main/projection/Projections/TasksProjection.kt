package ru.quipy.main.projection.Projections

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import kotlin.collections.ArrayList

@Document("TasksProjection")
data class TasksProjection(
    @Id
    val projectId: Int,
    val tasks: List<Task>,
) {

    data class Task(
        val projectId: Int,
        val taskTitle: String,
        val taskDescription: String,
        val taskStatus: String,
        val authorId: Int,
        val dedicatedTime: Date,
        val workersIds: ArrayList<Int>,
    )
}
