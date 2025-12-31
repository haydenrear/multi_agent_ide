package com.hayden.multiagentide.model.acp

import com.agentclientprotocol.model.ContentBlock
import com.hayden.multiagentide.infrastructure.EventBus
import com.hayden.multiagentide.infrastructure.EventListener
import com.hayden.multiagentide.model.events.Events
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.concurrent.CopyOnWriteArrayList

class AcpStreamWindowBufferTest {

    @Test
    fun `flushes message window into generation when switching types`() {
        val bus = RecordingEventBus()
        val buffer = AcpStreamWindowBuffer(bus)

        buffer.appendStreamWindow("node-1", AcpStreamWindowBuffer.StreamWindowType.MESSAGE, ContentBlock.Text("Hello"))
        val flushed = buffer.flushOtherWindows("node-1", AcpStreamWindowBuffer.StreamWindowType.THOUGHT)

        assertEquals(1, flushed.size)
        assertEquals("Hello", flushed.first().output.text)
        assertTrue(bus.events.any { it is Events.NodeStreamDeltaEvent })
    }

    @Test
    fun `publishes tool call events on flush`() {
        val bus = RecordingEventBus()
        val buffer = AcpStreamWindowBuffer(bus)

        val toolEvent = Events.ToolCallEvent(
            "evt-1",
            Instant.now(),
            "node-1",
            "tool-1",
            "search",
            "SEARCH",
            "PENDING",
            "START",
            emptyList(),
            emptyList(),
            null,
            null
        )

        buffer.appendEventWindow("node-1", AcpStreamWindowBuffer.StreamWindowType.TOOL_CALL, toolEvent)
        buffer.flushWindows("node-1")

        assertTrue(bus.events.contains(toolEvent))
    }

    @Test
    fun `publishes non-message updates on flush`() {
        val bus = RecordingEventBus()
        val buffer = AcpStreamWindowBuffer(bus)

        val planEvent = Events.PlanUpdateEvent(
            "plan-1",
            Instant.now(),
            "node-1",
            listOf(mapOf("content" to "step", "priority" to "HIGH", "status" to "PENDING"))
        )
        val modeEvent = Events.CurrentModeUpdateEvent(
            "mode-1",
            Instant.now(),
            "node-1",
            "analysis"
        )

        buffer.appendEventWindow("node-1", AcpStreamWindowBuffer.StreamWindowType.PLAN, planEvent)
        buffer.appendEventWindow("node-1", AcpStreamWindowBuffer.StreamWindowType.CURRENT_MODE, modeEvent)
        buffer.flushWindows("node-1")

        assertTrue(bus.events.contains(planEvent))
        assertTrue(bus.events.contains(modeEvent))
    }

    private class RecordingEventBus : EventBus {
        val events = CopyOnWriteArrayList<Events.GraphEvent>()

        override fun subscribe(listener: EventListener) {
        }

        override fun unsubscribe(listener: EventListener) {
        }

        override fun publish(event: Events.GraphEvent) {
            events.add(event)
        }

        override fun getSubscribers(): List<EventListener> = emptyList()

        override fun clear() {
            events.clear()
        }

        override fun hasSubscribers(): Boolean = false
    }
}
