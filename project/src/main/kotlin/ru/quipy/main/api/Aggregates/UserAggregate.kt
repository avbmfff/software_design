package ru.quipy.main.api.Aggregates

import ru.quipy.core.annotations.AggregateType
import ru.quipy.domain.Aggregate

@AggregateType(aggregateEventsTableName = "aggregate-user")
class UserAggregate : Aggregate
