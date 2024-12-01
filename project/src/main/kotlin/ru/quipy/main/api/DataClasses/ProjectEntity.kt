package ru.quipy.main.api.DataClasses

import java.util.*
import kotlin.collections.ArrayList

data class ProjectEntity(
    val projectId: Int,
    val projectTitle: String,
    val projectDescription: String,
    val authorId: Int,
    val startDate: Date,
    val endDate: Date,
    val workersIds: List<Int> = ArrayList<Int>(),
    val tasks: List<TaskEntity> = ArrayList<TaskEntity>(),
    val tasksStatuses: List<StatusEntity> = ArrayList<StatusEntity>(),
    val defaultStatus: StatusEntity = StatusEntity(),
)