package ru.quipy.main.configuration

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.quipy.core.EventSourcingServiceFactory
import ru.quipy.main.api.Aggregates.ProjectAggregate
import ru.quipy.main.api.Aggregates.UserAggregate
import ru.quipy.streams.AggregateEventStreamManager
import ru.quipy.streams.AggregateSubscriptionsManager
import ru.quipy.main.logic.AggregateStates.ProjectAggregateState
import ru.quipy.main.logic.AggregateStates.UsersAggregateState
import ru.quipy.main.projection.ProjectAggregateSubscriber
import ru.quipy.main.projection.Services.KanbanProjectionService
import ru.quipy.main.projection.UserAggregateSubscriber
import javax.annotation.PostConstruct

@Configuration
class EventSourcingLibConfiguration {

    private val logger = LoggerFactory.getLogger(EventSourcingLibConfiguration::class.java)

    @Autowired
    private lateinit var subscriptionsManager: AggregateSubscriptionsManager

    @Autowired
    private lateinit var eventSourcingServiceFactory: EventSourcingServiceFactory

    @Autowired
    private lateinit var eventStreamManager: AggregateEventStreamManager

    @Autowired
    private lateinit var kanbanProjectionService: KanbanProjectionService

    @Bean
    fun userService() = eventSourcingServiceFactory.create<String, UserAggregate, UsersAggregateState>()

    @Bean
    fun projectService() = eventSourcingServiceFactory.create<Int, ProjectAggregate, ProjectAggregateState>()

    @Autowired
    private lateinit var userAggregateSubscriber: UserAggregateSubscriber

    @Autowired
    private lateinit var projectAggregateSubscriber: ProjectAggregateSubscriber

    @PostConstruct
    fun init() {
        subscriptionsManager.subscribe<UserAggregate>(userAggregateSubscriber)
        subscriptionsManager.subscribe<ProjectAggregate>(projectAggregateSubscriber)
        eventStreamManager.maintenance {
            onRecordHandledSuccessfully { streamName, eventName ->
                logger.info("Stream $streamName successfully processed record of $eventName")
            }
        }
    }
}
