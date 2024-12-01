package ru.quipy.main.api.DataClasses

import ru.quipy.main.logic.AggregateStates.ProjectAggregateState
import java.util.*

data class RegistrationReqBody(
    val user_id: Int,
    val user_name: String,
    val login: String,
    val password: String,
)

data class AuthorizationReqBody(
    val login: String,
    val password: String,
)

class ProjectCreationBody(
    val projectId: Int,
    val projectTitle: String,
    val projectDescription: String,
    val authorId: Int,
    val startDate: Date,
    val endDate: Date,
)

class ProjectWorkerBody(
    val projectId: Int,
    val workerId: Int,
    val workersIds: ArrayList<Int>,
)

class TaskCreationBody(
    val projectId: Int,
    val taskTitle: String,
    val taskDescription: String,
    val authorId: Int,
    val taskStatus: String,
    val dedicatedTime: Date,
)

class TaskUpdatingBody(
    val projectId: Int,
    val taskTitle: String,
    val taskDescription: String,
    val taskStatus: String,
)

class TaskDeletionBody(
    val projectId: Int,
    val taskTitle: String,
)

class StatusCreationBody(
    val projectId: Int,
    val status: String,
)

class StatusDeletionBody(
    val projectId: Int,
    val statusName: String,
)
