package ru.quipy.main.projection.Projections

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document("UserProjection")
data class UserProjection(
    @Id
    val id: Int,
    val login: String = "",
    val user_name: String = "",
    val projects: List<Int> = ArrayList(),
)
