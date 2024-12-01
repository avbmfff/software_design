package ru.quipy.main.logic.AggregateStates

import org.testcontainers.shaded.com.google.common.annotations.VisibleForTesting
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import ru.quipy.main.api.Aggregates.UserAggregate
import ru.quipy.main.api.AggregateEvents.UserAuthorizedEvent
import ru.quipy.main.api.AggregateEvents.UserRegisteredEvent
import ru.quipy.main.api.DataClasses.UserEntity

class UsersAggregateState : AggregateState<String, UserAggregate> {

    @VisibleForTesting
    @Volatile
    var user: UserEntity? = null

    @VisibleForTesting
    var isAuthorized: Boolean = false

    override fun getId(): String = user?.login.orEmpty()

    fun register(
        user_id: Int,
        user_name: String,
        login: String,
        password: String,
    ): UserRegisteredEvent {
        return UserRegisteredEvent(
            user_id = user_id,
            user_name = user_name,
            login = login,
            password = password,
        )
    }

    fun authorize(
        login: String,
        password: String,
    ): UserAuthorizedEvent {
        if (user?.password != password)
            throw IllegalArgumentException("The password is wrong")
        if (isAuthorized)
            throw IllegalArgumentException("The user is already authorized")
        return UserAuthorizedEvent(
            login = login,
            password = password,
        )
    }

    @StateTransitionFunc
    fun UserRegisteredEventTransition(event: UserRegisteredEvent) {
        user = UserEntity(
            user_name = event.user_name,
            login = event.login,
            password = event.password,
        )
    }

    @StateTransitionFunc
    fun UserAuthorizedEventTransition(event: UserAuthorizedEvent) {
        isAuthorized = true
    }


}
