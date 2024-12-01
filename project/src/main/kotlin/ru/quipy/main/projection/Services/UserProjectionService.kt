package ru.quipy.main.projection.Services

import org.springframework.stereotype.Service
import ru.quipy.main.api.AggregateEvents.UserRegisteredEvent
import ru.quipy.main.projection.Repositories.UserProjectionRepository
import ru.quipy.main.projection.Projections.UserProjection

@Service
class UserProjectionService(
    private val repository: UserProjectionRepository,
) {

    fun getAll(): List<UserProjection> = repository.findAll()

    fun addUser(
        event: UserRegisteredEvent
    ) {
        repository.save(
            UserProjection(
                id = event.user_id,
                user_name = event.user_name,
                login = event.login,
                projects = ArrayList(),
            )
        )
    }

    fun addProject(
        id: Int,
        projectId: Int
    ) {
        val user = repository.findById(id).get()
        repository.save(user.copy(projects = user.projects.plus(projectId)))
    }
}
