package ru.quipy.main.projection.Projections

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("KanbanProjection")
data class KanbanProjection(
    @Id
    val statusName: String,
    val tasks: List<Task>,
) {

    data class Task(
        val name: String,
    )
}
