package ru.quipy.main.projection.Repositories

import org.springframework.data.mongodb.repository.MongoRepository
import ru.quipy.main.projection.Projections.KanbanProjection

interface KanbanProjectionRepository : MongoRepository<KanbanProjection, String>
