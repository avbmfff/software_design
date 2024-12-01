package ru.quipy.main.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.quipy.core.EventSourcingService
import ru.quipy.main.api.AggregateEvents.UserAuthorizedEvent
import ru.quipy.main.api.AggregateEvents.UserRegisteredEvent
import ru.quipy.main.api.Aggregates.UserAggregate
import ru.quipy.main.api.DataClasses.AuthorizationReqBody
import ru.quipy.main.api.DataClasses.RegistrationReqBody
import ru.quipy.main.logic.AggregateStates.UsersAggregateState
import ru.quipy.main.projection.Services.UserProjectionService


@RestController
@RequestMapping("/user")
class UserController(
    val userService: EventSourcingService<String, UserAggregate, UsersAggregateState>,
    val userProjectionService: UserProjectionService,
) {

    @GetMapping("/GetallUsers")
    fun getAll() = userProjectionService.getAll()

    @PostMapping("/registerUser")
    fun registerUser(@RequestBody body: RegistrationReqBody): UserRegisteredEvent { userService.getStateOfVersion(
            aggregateId = body.login,
            version = 1L)?.let { throw IllegalArgumentException("The login is already registered") }
        return userService.create { state -> state.register(
                user_id = body.user_id,
                user_name = body.user_name,
                login = body.login,
                password = body.password,) }
    }

    @PostMapping("/authorizeUser")
    fun authorized(@RequestBody body: AuthorizationReqBody): UserAuthorizedEvent {
        return userService.update(
            aggregateId = body.login
        ) { state ->
            state.authorize(
                login = body.login,
                password = body.password,
            )
        }

    }
}
