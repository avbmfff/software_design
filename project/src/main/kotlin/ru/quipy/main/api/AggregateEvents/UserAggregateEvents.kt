package ru.quipy.main.api.AggregateEvents

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import ru.quipy.main.api.Aggregates.UserAggregate

private const val USER_REGISTERED_EVENT = "USER_REGISTERED_EVENT"
private const val USER_AUTHORIZED_EVENT = "USER_AUTHORIZED_EVENT"

@DomainEvent(name = USER_REGISTERED_EVENT)
class UserRegisteredEvent(
    val user_id: Int,
    val user_name: String,
    val login: String,
    val password: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<UserAggregate>(
    name = USER_REGISTERED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = USER_AUTHORIZED_EVENT)
class UserAuthorizedEvent(
    val login: String,
    val password: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<UserAggregate>(
    name = USER_AUTHORIZED_EVENT,
    createdAt = createdAt,
)
