package com.hayden.multiagentide.service

import com.agentclientprotocol.model.PermissionOption
import com.agentclientprotocol.model.RequestPermissionOutcome
import com.agentclientprotocol.model.RequestPermissionResponse
import com.agentclientprotocol.model.SessionUpdate
import com.hayden.multiagentide.orchestration.ComputationGraphOrchestrator
import com.hayden.multiagentide.repository.GraphRepository
import com.hayden.multiagentidelib.infrastructure.EventBus
import com.hayden.multiagentidelib.model.events.Events
import com.hayden.multiagentidelib.model.nodes.AskPermissionNode
import com.hayden.multiagentidelib.model.nodes.GraphNode
import com.hayden.multiagentidelib.service.IPermissionGate
import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.json.JsonElement
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Service
class PermissionGate(
    private val graphRepository: GraphRepository,
    private val orchestrator: ComputationGraphOrchestrator,
    private val eventBus: EventBus
) : IPermissionGate {

    private val pendingRequests = ConcurrentHashMap<String, IPermissionGate.PendingPermissionRequest>()

    override fun publishRequest(
        requestId: String,
        originNodeId: String,
        toolCall: SessionUpdate.ToolCallUpdate,
        permissions: List<PermissionOption>,
        meta: JsonElement?
    ): IPermissionGate.PendingPermissionRequest {
        val existing = pendingRequests[requestId]
        if (existing != null) {
            return existing
        }

        val now = Instant.now()
        val permissionNodeId = UUID.randomUUID().toString()
//        val permissionNode = AskPermissionNode.builder()
//            .nodeId(permissionNodeId)
//            .title("Permission: " + (toolCall.title ?: "request"))
//            .goal("Permission requested for tool call " + toolCall.toolCallId.value)
//            .status(GraphNode.NodeStatus.WAITING_INPUT)
//            .parentNodeId(originNodeId)
//            .childNodeIds(mutableListOf())
//            .metadata(
//                mutableMapOf(
//                    "requestId" to requestId,
//                    "toolCallId" to toolCall.toolCallId.value,
//                    "originNodeId" to originNodeId
//                )
//            )
//            .createdAt(now)
//            .lastUpdatedAt(now)
//            .toolCallId(toolCall.toolCallId.value)
//            .optionIds(permissions.map { it.optionId.toString() })
//            .build()
        val permissionNode = null;

        try {
            orchestrator.addChildNodeAndEmitEvent(originNodeId, permissionNode)
        } catch (ex: Exception) {
            graphRepository.save(permissionNode)
        }

        val deferred = CompletableDeferred<RequestPermissionResponse>()
        val pending = IPermissionGate.PendingPermissionRequest(
            requestId = requestId,
            originNodeId = originNodeId,
            toolCallId = toolCall.toolCallId.value,
            permissions = permissions,
            deferred = deferred,
            meta = meta,
            nodeId = permissionNodeId
        )

        pendingRequests[requestId] = pending

        eventBus.publish(
            Events.PermissionRequestedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                permissionNodeId,
                originNodeId,
                requestId,
                toolCall.toolCallId.value,
                permissions
            )
        )

        return pending
    }

    override suspend fun awaitResponse(requestId: String): RequestPermissionResponse {
        val pending = pendingRequests[requestId] ?: return RequestPermissionResponse(
            RequestPermissionOutcome.Cancelled,
            null
        )
        return pending.deferred.await()
    }

    override fun resolveSelected(requestId: String, optionId: String?): Boolean {
        val pending = pendingRequests.remove(requestId) ?: return false
        val selected = resolveSelectedOption(pending.permissions, optionId)
        val outcome = if (selected == null) {
            RequestPermissionOutcome.Cancelled
        } else {
            RequestPermissionOutcome.Selected(selected.optionId)
        }
        completePending(pending, outcome, selected?.optionId?.toString())
        return true
    }

    override fun resolveCancelled(requestId: String): Boolean {
        val pending = pendingRequests.remove(requestId) ?: return false
        completePending(pending, RequestPermissionOutcome.Cancelled, null)
        return true
    }

    override fun resolveSelectedOption(
        permissions: List<PermissionOption>,
        optionId: String?
    ): PermissionOption? {
        if (permissions.isEmpty()) {
            return null
        }
        if (optionId.isNullOrBlank()) {
            return permissions.first()
        }
        return permissions.firstOrNull { it.optionId.toString() == optionId }
            ?: permissions.firstOrNull()
    }

    override fun completePending(
        pending: IPermissionGate.PendingPermissionRequest,
        outcome: RequestPermissionOutcome,
        selectedOptionId: String?
    ) {
        val response = RequestPermissionResponse(outcome, pending.meta)
        pending.deferred.complete(response)

        val nodeId = pending.nodeId
        if (nodeId != null) {
            val node = graphRepository.findById(nodeId).orElse(null)
            if (node is AskPermissionNode) {
                val updated = node.withStatus(GraphNode.NodeStatus.COMPLETED)
                graphRepository.save(updated)
                orchestrator.emitStatusChangeEvent(
                    nodeId,
                    node.status(),
                    GraphNode.NodeStatus.COMPLETED,
                    "Permission resolved"
                )
            }
        }

        eventBus.publish(
            Events.PermissionResolvedEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                pending.nodeId,
                pending.originNodeId,
                pending.requestId,
                pending.toolCallId,
                outcome.toString(),
                selectedOptionId
            )
        )
    }
}