package com.hayden.multiagentide.tui;

import com.hayden.acp_cdc_ai.acp.events.ArtifactKey;
import com.hayden.acp_cdc_ai.acp.events.EventBus;
import com.hayden.acp_cdc_ai.acp.events.EventListener;
import com.hayden.acp_cdc_ai.acp.events.Events;
import com.hayden.acp_cdc_ai.permission.IPermissionGate;
import com.hayden.multiagentide.cli.CliEventFormatter;
import com.hayden.multiagentide.repository.EventStreamRepository;
import lombok.RequiredArgsConstructor;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.component.view.TerminalUI;
import org.springframework.shell.component.view.TerminalUIBuilder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Profile("cli")
@RequiredArgsConstructor
public class TuiSession implements EventListener {

    @FunctionalInterface
    public interface GoalStarter {
        String startGoal(String goal);
    }

    private final EventStreamRepository eventStreamRepository;
    private final IPermissionGate permissionGateAdapter;
    private final CliEventFormatter eventFormatter;
    private final Terminal terminal;

    private final Object stateLock = new Object();
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final TuiStateReducer reducer = new TuiStateReducer();

    private EventBus eventBus;

    private GoalStarter goalStarter;
    private String shellSessionId;
    private String activeSessionId;
    private String activeNodeId;

    private TerminalUI terminalUi;
    private TuiTerminalView rootView;
    private TuiState state;
    private final Map<String, Boolean> startedSessions = new LinkedHashMap<>();
    private volatile int eventListHeight = 10;

    @Autowired
    @Lazy
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void configureSession(String initialSessionId, GoalStarter goalStarter) {
        this.goalStarter = goalStarter;
        this.activeSessionId = initialSessionId;
        this.activeNodeId = initialSessionId;
        this.startedSessions.put(initialSessionId, false);
    }

    public void run() {
        if (!running.compareAndSet(false, true)) {
            return;
        }

        try {
            this.shellSessionId = UUID.randomUUID().toString();
            ensureInitialState();

            this.terminalUi = new TerminalUIBuilder(terminal).build();
            this.rootView = new TuiTerminalView(
                    this::snapshotState,
                    eventFormatter,
                    eventStreamRepository,
                    new ViewController(),
                    height -> eventListHeight = Math.max(1, height)
            );
            terminalUi.configure(rootView);
            terminalUi.setRoot(rootView, true);
            terminalUi.run();
        } finally {
            running.set(false);
            terminalUi = null;
            rootView = null;
        }
    }

    @Override
    public String listenerId() {
        return "cli-tui-session";
    }

    @Override
    public boolean isInterestedIn(Events.GraphEvent eventType) {
        return running.get();
    }

    @Override
    public void onEvent(Events.GraphEvent event) {
        if (!running.get()) {
            return;
        }

        Events.AddMessageEvent queuedMessage = null;
        synchronized (stateLock) {
            TuiViewport viewport = new TuiViewport(Math.max(1, eventListHeight));
            state = reducer.reduce(state, event, viewport, resolveSessionId(event));
            queuedMessage = resolveInteractionMessage(event);
        }

        if (terminalUi != null) {
            terminalUi.redraw();
        }
        if (queuedMessage != null) {
            eventBus.publish(queuedMessage);
        }
    }

    private void ensureInitialState() {
        synchronized (stateLock) {
            if (activeSessionId == null || activeSessionId.isBlank()) {
                activeSessionId = "session-" + UUID.randomUUID();
                activeNodeId = activeSessionId;
                startedSessions.put(activeSessionId, false);
            }
            Map<String, TuiSessionState> sessions = new LinkedHashMap<>();
            sessions.put(activeSessionId, TuiSessionState.initial());
            state = TuiState.initial(shellSessionId, activeSessionId, List.of(activeSessionId), sessions);
        }
    }

    private TuiState snapshotState() {
        synchronized (stateLock) {
            return state;
        }
    }

    private TuiSessionState activeSessionState() {
        if (state == null || state.activeSessionId() == null) {
            return TuiSessionState.initial();
        }
        return state.sessions().getOrDefault(state.activeSessionId(), TuiSessionState.initial());
    }

    private void publishInteraction(Events.TuiInteractionEvent event) {
        String nodeId = (activeNodeId == null || activeNodeId.isBlank())
                ? (activeSessionId == null ? shellSessionId : activeSessionId)
                : activeNodeId;
        String interactionSessionId = state != null && state.activeSessionId() != null
                ? state.activeSessionId()
                : activeSessionId;

        Events.TuiInteractionGraphEvent graphEvent = new Events.TuiInteractionGraphEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                nodeId,
                interactionSessionId,
                event
        );
        eventBus.publish(graphEvent);
    }

    private Events.AddMessageEvent resolveInteractionMessage(Events.GraphEvent event) {
        if (!(event instanceof Events.TuiInteractionGraphEvent interaction)) {
            return null;
        }
        if (!(interaction.tuiEvent() instanceof Events.ChatInputSubmitted submitted)) {
            return null;
        }

        String text = submitted.text();
        if (text == null || text.isBlank()) {
            return null;
        }

        String currentSession = state.activeSessionId();
        boolean goalStarted = startedSessions.getOrDefault(currentSession, false);
        if (!goalStarted) {
            startGoalForCurrentSession(text.trim(), currentSession);
            return null;
        }

        if (handlePendingPermissions(text.trim()) || handlePendingInterrupts(text.trim())) {
            return null;
        }

        return new Events.AddMessageEvent(
                UUID.randomUUID().toString(),
                Instant.now(),
                activeNodeId,
                text
        );
    }

    private void startGoalForCurrentSession(String goal, String sessionIdToReplace) {
        if (goalStarter == null || sessionIdToReplace == null) {
            return;
        }
        String startedNodeId = goalStarter.startGoal(goal);
        if (startedNodeId == null || startedNodeId.isBlank()) {
            return;
        }

        Map<String, TuiSessionState> sessions = new LinkedHashMap<>(state.sessions());
        TuiSessionState existing = sessions.getOrDefault(sessionIdToReplace, TuiSessionState.initial());
        sessions.remove(sessionIdToReplace);
        sessions.put(startedNodeId, existing);

        List<String> order = new ArrayList<>(state.sessionOrder());
        int idx = order.indexOf(sessionIdToReplace);
        if (idx >= 0) {
            order.set(idx, startedNodeId);
        } else if (!order.contains(startedNodeId)) {
            order.add(startedNodeId);
        }

        startedSessions.remove(sessionIdToReplace);
        startedSessions.put(startedNodeId, true);

        activeSessionId = startedNodeId;
        activeNodeId = startedNodeId;
        state = new TuiState(
                state.sessionId(),
                startedNodeId,
                List.copyOf(order),
                sessions,
                TuiFocus.CHAT_INPUT,
                state.chatScrollOffset()
        );
    }

    private boolean handlePendingPermissions(String input) {
        List<IPermissionGate.PendingPermissionRequest> pending = permissionGateAdapter.pendingPermissionRequests();
        if (pending == null || pending.isEmpty()) {
            return false;
        }
        IPermissionGate.PendingPermissionRequest request = pending.get(0);
        if ("cancel".equalsIgnoreCase(input)) {
            permissionGateAdapter.resolveCancelled(request.getRequestId());
            return true;
        }
        List<com.agentclientprotocol.model.PermissionOption> options = request.getPermissions();
        if (options != null && !options.isEmpty()) {
            try {
                int index = Integer.parseInt(input);
                if (index >= 1 && index <= options.size()) {
                    permissionGateAdapter.resolveSelected(request.getRequestId(), options.get(index - 1));
                    return true;
                }
            } catch (NumberFormatException ignored) {
            }
        }
        permissionGateAdapter.resolveSelected(request.getRequestId(), input);
        return true;
    }

    private boolean handlePendingInterrupts(String input) {
        List<IPermissionGate.PendingInterruptRequest> pending = permissionGateAdapter.pendingInterruptRequests();
        if (pending == null || pending.isEmpty()) {
            return false;
        }
        IPermissionGate.PendingInterruptRequest request = pending.get(0);
        String resolutionType;
        String resolutionNotes;
        if (input.isBlank()) {
            resolutionType = "resolved";
            resolutionNotes = "";
        } else if ("approve".equalsIgnoreCase(input) || "approved".equalsIgnoreCase(input)) {
            resolutionType = "approved";
            resolutionNotes = "approved";
        } else {
            resolutionType = "feedback";
            resolutionNotes = input;
        }
        permissionGateAdapter.resolveInterrupt(request.getInterruptId(), resolutionType, resolutionNotes, null);
        return true;
    }

    private String resolveSessionId(Events.GraphEvent event) {
        if (event instanceof Events.TuiInteractionGraphEvent interaction) {
            return interaction.sessionId();
        }
        String nodeId = event == null ? null : event.nodeId();
        if (nodeId == null || nodeId.isBlank()) {
            return state == null ? activeSessionId : state.activeSessionId();
        }
        try {
            return new ArtifactKey(nodeId).root().value();
        } catch (Exception ignored) {
            return nodeId;
        }
    }

    private void moveSessionSelection(int delta) {
        List<String> order = state.sessionOrder();
        if (order.isEmpty()) {
            return;
        }
        int index = order.indexOf(state.activeSessionId());
        if (index < 0) {
            index = 0;
        }
        int next = Math.max(0, Math.min(order.size() - 1, index + delta));
        publishInteraction(new Events.SessionSelected(order.get(next)));
    }

    private void createNewSession() {
        String newSessionId = "session-" + UUID.randomUUID();
        startedSessions.put(newSessionId, false);
        publishInteraction(new Events.SessionCreated(newSessionId));
        publishInteraction(new Events.SessionSelected(newSessionId));
        publishInteraction(new Events.FocusChatInput(TuiFocus.SESSION_LIST.name()));
    }

    private final class ViewController implements TuiTerminalView.Controller {

        @Override
        public void moveSelection(int delta) {
            synchronized (stateLock) {
                if (state.focus() == TuiFocus.SESSION_LIST) {
                    moveSessionSelection(delta);
                    return;
                }
                if (state.focus() == TuiFocus.CHAT_SEARCH) {
                    publishInteraction(new Events.ChatSearchResultNavigate(delta, activeSessionState().chatSearch().selectedResultIndex()));
                    return;
                }
                if (state.focus() == TuiFocus.EVENT_STREAM) {
                    int target = activeSessionState().selectedIndex() + delta;
                    publishInteraction(new Events.EventStreamMoveSelection(delta, target));
                    return;
                }
                publishInteraction(new Events.FocusEventStream(state.focus().name()));
            }
        }

        @Override
        public void scrollList(int delta) {
            synchronized (stateLock) {
                if (state.focus() != TuiFocus.EVENT_STREAM) {
                    return;
                }
                int target = activeSessionState().scrollOffset() + delta;
                publishInteraction(new Events.EventStreamScroll(delta, target));
            }
        }

        @Override
        public void handleEnter() {
            synchronized (stateLock) {
                if (state.focus() == TuiFocus.CHAT_INPUT) {
                    publishInteraction(new Events.ChatInputSubmitted(activeSessionState().chatInput()));
                    return;
                }
                if (state.focus() == TuiFocus.SESSION_LIST) {
                    publishInteraction(new Events.SessionSelected(state.activeSessionId()));
                    return;
                }
                if (state.focus() == TuiFocus.CHAT_SEARCH) {
                    publishInteraction(new Events.ChatSearchResultNavigate(1, activeSessionState().chatSearch().selectedResultIndex()));
                    return;
                }
                if (state.focus() == TuiFocus.EVENT_STREAM) {
                    TuiSessionState sessionState = activeSessionState();
                    if (!sessionState.events().isEmpty()) {
                        Events.GraphEvent selected = sessionState.events().get(sessionState.selectedIndex());
                        publishInteraction(new Events.EventStreamOpenDetail(selected.eventId()));
                    }
                }
            }
        }

        @Override
        public void handleBackspace() {
            synchronized (stateLock) {
                TuiSessionState sessionState = activeSessionState();
                if (sessionState.detailOpen()) {
                    publishInteraction(new Events.EventStreamCloseDetail(sessionState.detailEventId()));
                    return;
                }
                if (state.focus() == TuiFocus.CHAT_INPUT) {
                    String text = sessionState.chatInput();
                    if (!text.isEmpty()) {
                        String next = text.substring(0, text.length() - 1);
                        publishInteraction(new Events.ChatInputChanged(next, next.length()));
                    }
                    return;
                }
                if (state.focus() == TuiFocus.CHAT_SEARCH && sessionState.chatSearch().active()) {
                    String query = sessionState.chatSearch().query();
                    if (!query.isEmpty()) {
                        String next = query.substring(0, query.length() - 1);
                        publishInteraction(new Events.ChatSearchQueryChanged(next, next.length()));
                    }
                }
            }
        }

        @Override
        public void handleEscape() {
            synchronized (stateLock) {
                TuiSessionState sessionState = activeSessionState();
                if (sessionState.detailOpen()) {
                    publishInteraction(new Events.EventStreamCloseDetail(sessionState.detailEventId()));
                    return;
                }
                if (state.focus() == TuiFocus.CHAT_SEARCH && sessionState.chatSearch().active()) {
                    publishInteraction(new Events.ChatSearchClosed(sessionState.chatSearch().query()));
                }
            }
        }

        @Override
        public void toggleFocus() {
            synchronized (stateLock) {
                if (state.focus() == TuiFocus.EVENT_STREAM || state.focus() == TuiFocus.SESSION_LIST) {
                    publishInteraction(new Events.FocusChatInput(state.focus().name()));
                } else {
                    publishInteraction(new Events.FocusEventStream(state.focus().name()));
                }
            }
        }

        @Override
        public void focusSessionList() {
            synchronized (stateLock) {
                publishInteraction(new Events.FocusSessionList(state.focus().name()));
            }
        }

        @Override
        public void createNewSession() {
            synchronized (stateLock) {
                TuiSession.this.createNewSession();
            }
        }

        @Override
        public void focusEventStream() {
            synchronized (stateLock) {
                publishInteraction(new Events.FocusEventStream(state.focus().name()));
            }
        }

        @Override
        public void openSearch() {
            synchronized (stateLock) {
                publishInteraction(new Events.ChatSearchOpened(activeSessionState().chatSearch().query()));
            }
        }

        @Override
        public void handlePrintable(char ch) {
            synchronized (stateLock) {
                if (state.focus() == TuiFocus.CHAT_INPUT) {
                    String next = activeSessionState().chatInput() + ch;
                    publishInteraction(new Events.ChatInputChanged(next, next.length()));
                    return;
                }
                if (state.focus() == TuiFocus.CHAT_SEARCH) {
                    String next = activeSessionState().chatSearch().query() + ch;
                    publishInteraction(new Events.ChatSearchQueryChanged(next, next.length()));
                }
            }
        }
    }

    public TuiState snapshotForTests() {
        synchronized (stateLock) {
            return state;
        }
    }
}
