package ru.quipy.main.api.DataClasses

import java.util.*
import kotlin.collections.ArrayList

data class TaskEntity(
    var title: String,
    var description: String,
    var status: StatusEntity,
    var authorId: Int,
    val dedicatedTime: Date,
    var workers: List<Int>
)