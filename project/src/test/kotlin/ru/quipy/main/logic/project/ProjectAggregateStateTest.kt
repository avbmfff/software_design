package ru.quipy.main.logic.project

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.quipy.main.logic.AggregateStates.ProjectAggregateState
import java.util.*

class ProjectAggregateStateTest {

    val projectName = "Test Project"

    @Test
    fun createProject_PositiveCase() {
        val projectAggregateState = ProjectAggregateState()
        val creatorId = UUID.randomUUID().toString()
        val participants = setOf(UUID.randomUUID().toString())

        val projectCreatedEvent = projectAggregateState.createProject(projectName, creatorId, participants)

        assertEquals(projectName, projectCreatedEvent.projectName)
        assertEquals(creatorId, projectCreatedEvent.creatorId)
        assertEquals(participants, projectCreatedEvent.participants)

        projectAggregateState.transitionProjectCreatedEvent(projectCreatedEvent)
        assertTrue(projectAggregateState.project?.name == projectName)
    }

    @Test
    fun createProject_NegativeCase() {
        val projectAggregateState = ProjectAggregateState()
        Assertions.assertFalse(projectAggregateState.project?.name == projectName)
    }

    @Test
    fun addParticipant_PositiveCase() {
        val creatorId = UUID.randomUUID().toString()
        val participants = setOf(UUID.randomUUID().toString())
        val projectAggregateState = ProjectAggregateState()
        val createProjectEvent = projectAggregateState.createProject(projectName, creatorId, setOf())
        projectAggregateState.transitionProjectCreatedEvent(createProjectEvent)

        val projectAddedParticipantEvent = projectAggregateState.addParticipant(projectName, creatorId, participants)
        projectAggregateState.transitionProjectAddedParticipantsEvent(projectAddedParticipantEvent)

        assertEquals(creatorId, projectAddedParticipantEvent.creatorId)
        assertTrue(projectAggregateState.project?.participantsIds!!.containsAll(participants))
    }

    @Test
    fun addParticipant_NegativeCase_NotExistingProject() {
        val creatorId = UUID.randomUUID().toString()
        val newParticipantId = UUID.randomUUID().toString()
        val projectAggregateState = ProjectAggregateState()

        assertThrows<IllegalStateException> {
            projectAggregateState.addParticipant(projectName, creatorId, setOf(newParticipantId))
        }
    }

    @Test
    fun addParticipant_NegativeCase_ExistingParticipant() {
        val projectName = "Test Project"
        val creatorId = UUID.randomUUID().toString()
        val participants = setOf(UUID.randomUUID().toString())
        val projectAggregateState = ProjectAggregateState()
        val event = projectAggregateState.createProject(projectName, creatorId, participants)
        projectAggregateState.transitionProjectCreatedEvent(event)

        assertThrows<IllegalStateException> {
            projectAggregateState.addParticipant(projectName, creatorId, participants)
        }
    }

    @Test
    fun testCreateTask_PositiveCase() {
        val creatorId = UUID.randomUUID().toString()
        val taskName = "task1"
        val executorId = UUID.randomUUID().toString()
        val estimatedEffort: Long = 2
        val priority = ProjectAggregateState.Task.Priority.LOW
        val projectAggregateState = ProjectAggregateState()
        val createProjectEvent = projectAggregateState.createProject(projectName, creatorId, setOf())
        projectAggregateState.transitionProjectCreatedEvent(createProjectEvent)

        val projectCreatedEvent =
            projectAggregateState.createTask(projectName, creatorId, taskName, executorId, estimatedEffort, priority)
        projectAggregateState.transitionProjectCreatedTaskEvent(projectCreatedEvent)

        assertEquals(creatorId, projectCreatedEvent.creatorId)
        assertTrue(projectAggregateState.project!!.tasks.containsKey(taskName))
    }

    @Test
    fun testCreateTask_NegativeCase_NonExistentProject() {
        val creatorId = UUID.randomUUID().toString()
        val taskName = "task1"
        val executorId = UUID.randomUUID().toString()
        val estimatedEffort: Long = 2
        val priority = ProjectAggregateState.Task.Priority.LOW
        val projectAggregateState = ProjectAggregateState()

        assertThrows<IllegalStateException> {
            projectAggregateState.createTask(projectName, creatorId, taskName, executorId, estimatedEffort, priority)
        }
    }

    @Test
    fun testUpdateTask_PositiveCase() {
        val creatorId = UUID.randomUUID().toString()
        val executorId = UUID.randomUUID().toString()
        val estimatedEffort: Long = 2
        val priority = ProjectAggregateState.Task.Priority.LOW
        val taskName = "task1"
        val projectName = "Test Project"
        val projectAggregateState = ProjectAggregateState()
        val createProjectEvent = projectAggregateState.createProject(projectName, creatorId, setOf())
        projectAggregateState.transitionProjectCreatedEvent(createProjectEvent)
        val projectCreatedEvent =
            projectAggregateState.createTask(projectName, creatorId, taskName, executorId, estimatedEffort, priority)
        projectAggregateState.transitionProjectCreatedTaskEvent(projectCreatedEvent)

        val projectUpdatedTaskEvent =
            projectAggregateState.updateTask(projectName, taskName, "In Progress", null, null, null)
        projectAggregateState.transitionProjectUpdatedTaskEvent(projectUpdatedTaskEvent)

        assertEquals("In Progress", projectUpdatedTaskEvent.statusName)
        assertTrue(
            projectAggregateState.project!!.tasks[taskName]!!.status!!.name == projectUpdatedTaskEvent.statusName
        )
    }

    @Test
    fun testUpdateTask_NegativeCase_NonExistentProject() {
        val taskName = "task1"
        val projectName = "NonExistentProject"
        val projectAggregateState = ProjectAggregateState()

        assertThrows<IllegalStateException> {
            projectAggregateState.updateTask(projectName, taskName, "In Progress", null, null, null)
        }
    }

    @Test
    fun testUpdateTask_NegativeCase_NonExistentStatus() {
        val creatorId = UUID.randomUUID().toString()
        val projectName = "Test Project"
        val taskName = "task1"
        val executorId = UUID.randomUUID().toString()
        val estimatedEffort: Long = 2
        val priority = ProjectAggregateState.Task.Priority.LOW
        val projectAggregateState = ProjectAggregateState()
        val createProjectEvent = projectAggregateState.createProject(projectName, creatorId, setOf())
        projectAggregateState.transitionProjectCreatedEvent(createProjectEvent)
        val projectCreatedEvent =
            projectAggregateState.createTask(projectName, creatorId, taskName, executorId, estimatedEffort, priority)
        projectAggregateState.transitionProjectCreatedTaskEvent(projectCreatedEvent)

        assertThrows<IllegalStateException> {
            projectAggregateState.updateTask(projectName, taskName, "NonExistentStatus", null, null, null)
        }
    }
}
