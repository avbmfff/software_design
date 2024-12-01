package ru.quipy.main.logic.user

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.quipy.main.logic.AggregateStates.UsersAggregateState

class UserAggregateStateTest {

    val usersAggregateState = UsersAggregateState()
    val login = "testUser"
    val password = "testPassword"
    val nickname = "testNick"

    @Test
    fun testRegister() {
        val userRegistrationEvent = usersAggregateState.register(login, password, nickname)
        Assertions.assertEquals(userRegistrationEvent.login, login)
        Assertions.assertEquals(userRegistrationEvent.password, password)
        Assertions.assertEquals(userRegistrationEvent.nickname, nickname)

        usersAggregateState.transitionUserRegistrationEvent(event = userRegistrationEvent)
        Assertions.assertEquals(true, usersAggregateState.user!!.login == login)
    }

    @Test
    fun testAuthorization() {
        val userRegistrationEvent = usersAggregateState.register(login, password, nickname)
        usersAggregateState.transitionUserRegistrationEvent(event = userRegistrationEvent)

        val userAuthorizationEvent = usersAggregateState.authorization(login, password)

        Assertions.assertEquals(userAuthorizationEvent.login, login)
        Assertions.assertEquals(userAuthorizationEvent.password, password)
        usersAggregateState.transitionUserAuthorizationEvent(userAuthorizationEvent)

        assertThrows<IllegalArgumentException> {
            usersAggregateState.authorization(login, password)
        }
    }
}
