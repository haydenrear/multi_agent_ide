package com.hayden.multiagentide.tui;

import com.hayden.acp_cdc_ai.acp.events.Events;
import com.hayden.multiagentide.cli.CliEventFormatter;
import com.hayden.multiagentide.repository.EventStreamRepository;
import org.springframework.shell.component.view.control.BoxView;
import org.springframework.shell.component.view.event.KeyEvent;
import org.springframework.shell.component.view.screen.Screen;
import org.springframework.shell.geom.Position;
import org.springframework.shell.geom.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

class TuiTerminalView extends BoxView {

    interface Controller {
        void moveSelection(int delta);

        void scrollList(int delta);

        void handleEnter();

        void handleBackspace();

        void handleEscape();

        void toggleFocus();

        void focusSessionList();

        void createNewSession();

        void focusEventStream();

        void openSearch();

        void handlePrintable(char ch);
    }

    private static final int ESCAPE_KEY = 27;
    private static final int CTRL_F = 6;
    private static final int CTRL_N = 14;
    private static final int CTRL_S = 19;
    private static final int CTRL_E = 5;

    private final Supplier<TuiState> stateSupplier;
    private final Controller controller;
    private final IntConsumer eventListHeightConsumer;
    private final SessionView sessionView;
    private final MessageStreamView messageStreamView;
    private final DetailView detailView;
    private final ChatView chatView;

    TuiTerminalView(
            Supplier<TuiState> stateSupplier,
            CliEventFormatter formatter,
            EventStreamRepository eventStreamRepository,
            Controller controller,
            IntConsumer eventListHeightConsumer
    ) {
        this.stateSupplier = stateSupplier;
        this.controller = controller;
        this.eventListHeightConsumer = eventListHeightConsumer;
        this.sessionView = new SessionView();
        this.messageStreamView = new MessageStreamView(formatter);
        this.detailView = new DetailView(formatter, eventStreamRepository);
        this.chatView = new ChatView();
        setShowBorder(false);
    }

    @Override
    protected void initInternal() {
        registerKeyBinding(KeyEvent.Key.CursorUp, () -> controller.moveSelection(-1));
        registerKeyBinding(KeyEvent.Key.CursorDown, () -> controller.moveSelection(1));
        registerKeyBinding(KeyEvent.Key.CursorLeft, () -> controller.scrollList(-5));
        registerKeyBinding(KeyEvent.Key.CursorRight, () -> controller.scrollList(5));
        registerKeyBinding(KeyEvent.Key.Tab, controller::toggleFocus);
        registerKeyBinding(KeyEvent.Key.Backtab, controller::toggleFocus);
        registerKeyBinding(KeyEvent.Key.Enter, controller::handleEnter);
        registerKeyBinding(KeyEvent.Key.Backspace, controller::handleBackspace);
        registerKeyBinding(ESCAPE_KEY, controller::handleEscape);
        registerKeyBinding(CTRL_F, controller::openSearch);
        registerKeyBinding(CTRL_E, controller::focusEventStream);
        registerKeyBinding(CTRL_S, controller::focusSessionList);
        registerKeyBinding(CTRL_N, controller::createNewSession);
        registerKeyBinding(KeyEvent.Key.Char, this::onChar);
    }

    @Override
    protected void drawInternal(Screen screen) {
        super.drawInternal(screen);

        TuiState state = stateSupplier.get();
        if (state == null) {
            return;
        }

        Rectangle rect = getRect();
        if (rect == null || rect.width() <= 0 || rect.height() <= 0) {
            return;
        }

        int width = Math.max(20, rect.width());
        int height = Math.max(6, rect.height());
        int originX = rect.x();
        int originY = rect.y();

        String activeSessionId = activeSessionId(state);
        TuiSessionState sessionState = sessionState(state, activeSessionId);

        int chatHeight = chatView.requiredHeight(state, sessionState, width);
        chatHeight = Math.max(2, Math.min(height - 2, chatHeight));

        int detailHeight = sessionState.detailOpen() ? Math.min(10, Math.max(4, height / 3)) : 0;
        int minBodyHeight = 2;
        if (detailHeight + chatHeight + minBodyHeight > height) {
            detailHeight = Math.max(0, height - chatHeight - minBodyHeight);
        }

        int bodyHeight = height - detailHeight - chatHeight;
        if (bodyHeight < minBodyHeight) {
            int reducibleChat = Math.max(0, chatHeight - 2);
            int needed = minBodyHeight - bodyHeight;
            int reduceBy = Math.min(reducibleChat, needed);
            chatHeight -= reduceBy;
            bodyHeight += reduceBy;
        }
        if (bodyHeight <= 0) {
            bodyHeight = 1;
        }

        int sessionWidth = Math.max(14, Math.min(32, width / 4));
        int minEventWidth = 20;
        if (width - sessionWidth - 1 < minEventWidth) {
            sessionWidth = Math.max(10, width - minEventWidth - 1);
        }
        int eventWidth = Math.max(1, width - sessionWidth - 1);

        List<String> lines = new ArrayList<>(height);

        if (detailHeight > 0) {
            lines.addAll(detailView.render(sessionState, width, detailHeight));
        }

        List<String> sessionLines = sessionView.render(state, sessionWidth, bodyHeight, activeSessionId);
        RenderedMessageStream renderedMessageStream = messageStreamView.render(state, sessionState, eventWidth, bodyHeight);
        eventListHeightConsumer.accept(Math.max(1, renderedMessageStream.visibleRows()));
        for (int i = 0; i < bodyHeight; i++) {
            String left = i < sessionLines.size() ? sessionLines.get(i) : TextLayout.pad("", sessionWidth);
            String right = i < renderedMessageStream.lines().size() ? renderedMessageStream.lines().get(i) : TextLayout.pad("", eventWidth);
            lines.add(left + "|" + right);
        }

        ChatRender chatRender = chatView.render(state, sessionState, width, chatHeight, originX, originY + detailHeight + bodyHeight);
        lines.addAll(chatRender.lines());

        while (lines.size() < height) {
            lines.add(TextLayout.pad("", width));
        }
        if (lines.size() > height) {
            lines = new ArrayList<>(lines.subList(0, height));
        }

        Screen.Writer writer = screen.writerBuilder().build();
        for (int i = 0; i < height; i++) {
            String line = i < lines.size() ? lines.get(i) : TextLayout.pad("", width);
            writer.text(TextLayout.pad(line, width), originX, originY + i);
        }

        Position cursor = chatRender.cursorPosition();
        int cursorX = Math.max(originX, Math.min(originX + width - 1, cursor.x()));
        int cursorY = Math.max(originY, Math.min(originY + height - 1, cursor.y()));
        screen.setShowCursor(true);
        screen.setCursorPosition(new Position(cursorX, cursorY));
    }

    private void onChar(KeyEvent event) {
        if (event == null || event.data() == null || event.data().isEmpty()) {
            return;
        }
        String data = event.data();
        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            if (ch == '\r' || ch == '\n') {
                controller.handleEnter();
                continue;
            }
            if (ch == 127 || ch == 8) {
                controller.handleBackspace();
                continue;
            }
            if (ch == 27) {
                controller.handleEscape();
                continue;
            }
            if (!event.hasCtrl() && ch >= 32) {
                controller.handlePrintable(ch);
            }
        }
    }

    private String activeSessionId(TuiState state) {
        if (state.activeSessionId() != null && !state.activeSessionId().isBlank()) {
            return state.activeSessionId();
        }
        if (!state.sessionOrder().isEmpty()) {
            return state.sessionOrder().get(0);
        }
        return null;
    }

    private TuiSessionState sessionState(TuiState state, String activeSessionId) {
        if (activeSessionId == null) {
            return TuiSessionState.initial();
        }
        return state.sessions().getOrDefault(activeSessionId, TuiSessionState.initial());
    }

    private static final class SessionView {

        private List<String> render(TuiState state, int width, int height, String activeSessionId) {
            List<String> lines = new ArrayList<>(height);
            String header = state.focus() == TuiFocus.SESSION_LIST ? "[Sessions] (focus)" : "[Sessions]";
            lines.add(TextLayout.pad(header, width));

            int available = Math.max(0, height - 1);
            int activeIndex = state.sessionOrder().indexOf(activeSessionId);
            if (activeIndex < 0) {
                activeIndex = 0;
            }
            int start = 0;
            if (available > 0 && activeIndex >= available) {
                start = activeIndex - available + 1;
            }

            for (int row = 0; row < available; row++) {
                int index = start + row;
                if (index >= state.sessionOrder().size()) {
                    lines.add(TextLayout.pad("", width));
                    continue;
                }
                String sessionId = state.sessionOrder().get(index);
                String prefix = sessionId.equals(activeSessionId) ? "> " : "  ";
                lines.add(TextLayout.pad(prefix + sessionId, width));
            }

            while (lines.size() < height) {
                lines.add(TextLayout.pad("", width));
            }
            return lines;
        }
    }

    private static final class MessageStreamView {

        private final CliEventFormatter formatter;

        private MessageStreamView(CliEventFormatter formatter) {
            this.formatter = formatter;
        }

        private RenderedMessageStream render(TuiState state, TuiSessionState sessionState, int width, int height) {
            List<String> lines = new ArrayList<>(height);
            String header = "[Events] total=" + sessionState.events().size();
            if (state.focus() == TuiFocus.EVENT_STREAM) {
                header += " (focus)";
            }
            lines.add(TextLayout.pad(header, width));

            int availableRows = Math.max(0, height - 1);
            int start = startEventIndex(sessionState, width, availableRows);
            for (int index = start; index < sessionState.events().size() && lines.size() < height; index++) {
                boolean selected = index == sessionState.selectedIndex();
                List<String> eventLines = formatEventLines(sessionState.events().get(index), width, selected);
                for (String line : eventLines) {
                    if (lines.size() >= height) {
                        break;
                    }
                    lines.add(TextLayout.pad(line, width));
                }
            }

            while (lines.size() < height) {
                lines.add(TextLayout.pad("", width));
            }

            return new RenderedMessageStream(lines, Math.max(1, availableRows));
        }

        private int startEventIndex(TuiSessionState sessionState, int width, int availableRows) {
            if (sessionState.events().isEmpty()) {
                return 0;
            }
            if (!sessionState.autoFollow()) {
                return Math.max(0, Math.min(sessionState.scrollOffset(), sessionState.events().size() - 1));
            }
            int remaining = availableRows;
            for (int index = sessionState.events().size() - 1; index >= 0; index--) {
                boolean selected = index == sessionState.selectedIndex();
                int lineCount = formatEventLines(sessionState.events().get(index), width, selected).size();
                remaining -= Math.max(1, lineCount);
                if (remaining < 0) {
                    return index + 1;
                }
            }
            return 0;
        }

        private List<String> formatEventLines(Events.GraphEvent event, int width, boolean selected) {
            int contentWidth = Math.max(1, width - 2);
            List<String> wrapped = TextLayout.wrapWord(formatter.format(event), contentWidth);
            List<String> lines = new ArrayList<>(wrapped.size());
            for (int i = 0; i < wrapped.size(); i++) {
                String prefix = i == 0 ? (selected ? "> " : "  ") : "  ";
                lines.add(prefix + wrapped.get(i));
            }
            return lines;
        }
    }

    private static final class DetailView {

        private final CliEventFormatter formatter;
        private final EventStreamRepository eventStreamRepository;

        private DetailView(CliEventFormatter formatter, EventStreamRepository eventStreamRepository) {
            this.formatter = formatter;
            this.eventStreamRepository = eventStreamRepository;
        }

        private List<String> render(TuiSessionState sessionState, int width, int height) {
            List<String> lines = new ArrayList<>(height);
            lines.add(TextLayout.pad("[Detail]", width));
            String detail = resolveDetailText(sessionState);
            List<String> detailLines = new ArrayList<>();
            for (String line : detail.split("\\R")) {
                detailLines.addAll(TextLayout.wrapWord(line, width));
            }

            for (int i = 1; i < height; i++) {
                String line = i - 1 < detailLines.size() ? detailLines.get(i - 1) : "";
                lines.add(TextLayout.pad(line, width));
            }
            return lines;
        }

        private String resolveDetailText(TuiSessionState sessionState) {
            String eventId = sessionState.detailEventId();
            if (eventId == null || eventId.isBlank()) {
                return "(no detail)";
            }
            Optional<Events.GraphEvent> fromRepository = eventStreamRepository.findById(eventId);
            if (fromRepository.isPresent()) {
                Events.GraphEvent event = fromRepository.get();
                return event.eventType() + " id=" + event.eventId() + " node=" + event.nodeId() + "\n" + formatter.format(event);
            }
            for (Events.GraphEvent event : sessionState.events()) {
                if (eventId.equals(event.eventId())) {
                    return event.eventType() + " id=" + event.eventId() + " node=" + event.nodeId() + "\n" + formatter.format(event);
                }
            }
            return "(event not found)";
        }
    }

    private static final class ChatView {

        private int requiredHeight(TuiState state, TuiSessionState sessionState, int width) {
            ChatLine chatLine = resolveChatLine(state, sessionState);
            List<String> wrapped = TextLayout.wrapFixed(chatLine.prompt() + chatLine.value(), Math.max(1, width));
            return Math.max(2, 1 + Math.max(1, wrapped.size()));
        }

        private ChatRender render(TuiState state, TuiSessionState sessionState, int width, int height, int originX, int originY) {
            ChatLine chatLine = resolveChatLine(state, sessionState);
            List<String> wrapped = TextLayout.wrapFixed(chatLine.prompt() + chatLine.value(), Math.max(1, width));
            if (wrapped.isEmpty()) {
                wrapped = List.of("");
            }

            List<String> lines = new ArrayList<>(height);
            lines.add(TextLayout.pad("-".repeat(Math.max(3, width)), width));
            lines.addAll(wrapped);
            while (lines.size() < height) {
                lines.add(TextLayout.pad("", width));
            }
            if (lines.size() > height) {
                lines = new ArrayList<>(lines.subList(0, height));
            }

            int cursorOffset = chatLine.prompt().length() + chatLine.value().length();
            int cursorRow = originY + 1 + (cursorOffset / Math.max(1, width));
            int cursorColumn = originX + (cursorOffset % Math.max(1, width));
            int maxRow = originY + height - 1;
            int maxColumn = originX + width - 1;
            Position cursorPosition = new Position(
                    Math.max(originX, Math.min(maxColumn, cursorColumn)),
                    Math.max(originY, Math.min(maxRow, cursorRow))
            );

            return new ChatRender(lines, cursorPosition);
        }

        private ChatLine resolveChatLine(TuiState state, TuiSessionState sessionState) {
            if (state.focus() == TuiFocus.CHAT_SEARCH && sessionState.chatSearch().active()) {
                return new ChatLine("Search> ", sessionState.chatSearch().query());
            }
            return new ChatLine("Chat> ", sessionState.chatInput());
        }
    }

    private record ChatLine(String prompt, String value) {
    }

    private record ChatRender(List<String> lines, Position cursorPosition) {
    }

    private record RenderedMessageStream(List<String> lines, int visibleRows) {
    }

    private static final class TextLayout {

        private static String pad(String line, int width) {
            String trimmed = trim(line, width);
            if (trimmed.length() >= width) {
                return trimmed;
            }
            return trimmed + " ".repeat(width - trimmed.length());
        }

        private static String trim(String line, int width) {
            if (line == null) {
                return "";
            }
            if (line.length() <= width) {
                return line;
            }
            return line.substring(0, Math.max(0, width));
        }

        private static List<String> wrapWord(String text, int width) {
            if (text == null || text.isEmpty()) {
                return List.of("");
            }
            int maxWidth = Math.max(1, width);
            List<String> lines = new ArrayList<>();
            String remaining = text;
            while (!remaining.isEmpty()) {
                if (remaining.length() <= maxWidth) {
                    lines.add(remaining);
                    break;
                }
                int breakAt = remaining.lastIndexOf(' ', maxWidth);
                if (breakAt <= 0) {
                    breakAt = maxWidth;
                }
                String head = remaining.substring(0, breakAt).trim();
                if (head.isEmpty()) {
                    head = remaining.substring(0, Math.min(maxWidth, remaining.length()));
                    breakAt = head.length();
                }
                lines.add(head);
                remaining = remaining.substring(Math.min(remaining.length(), breakAt)).trim();
            }
            return lines.isEmpty() ? List.of("") : lines;
        }

        private static List<String> wrapFixed(String text, int width) {
            if (text == null || text.isEmpty()) {
                return List.of("");
            }
            int maxWidth = Math.max(1, width);
            List<String> lines = new ArrayList<>();
            int i = 0;
            while (i < text.length()) {
                int end = Math.min(text.length(), i + maxWidth);
                lines.add(text.substring(i, end));
                i = end;
            }
            return lines;
        }
    }
}
