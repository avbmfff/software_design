package ru.quipy.main.projection

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.quipy.main.api.AggregateEvents.UserRegisteredEvent
import ru.quipy.streams.annotation.AggregateSubscriber
import ru.quipy.streams.annotation.SubscribeEvent
import ru.quipy.main.api.Aggregates.UserAggregate
import ru.quipy.main.projection.Services.UserProjectionService

@Service
@AggregateSubscriber(
    aggregateClass = UserAggregate::class,
    subscriberName = "user-aggregate-subscriber"
)
class UserAggregateSubscriber {

    @Autowired
    lateinit var userProjectionService: UserProjectionService

    @SubscribeEvent
    fun onUserCreated(event: UserRegisteredEvent) {
        userProjectionService.addUser(event)
    }
}
