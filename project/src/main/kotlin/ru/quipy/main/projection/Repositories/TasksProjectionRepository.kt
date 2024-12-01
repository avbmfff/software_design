package ru.quipy.main.projection.Repositories

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import ru.quipy.main.projection.Projections.TasksProjection

@Repository
interface TasksProjectionRepository : MongoRepository<TasksProjection, Int>
