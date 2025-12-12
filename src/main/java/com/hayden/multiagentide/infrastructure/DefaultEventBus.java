package com.hayden.multiagentide.infrastructure;

import com.hayden.multiagentide.model.mixins.Events;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Default implementation of EventBus using CopyOnWriteArrayList for thread-safe
 * concurrent iteration while allowing concurrent modifications.
 */
@Service
public class DefaultEventBus implements EventBus {

    private final CopyOnWriteArrayList<EventListener> subscribers = new CopyOnWriteArrayList<>();

    @Override
    public void subscribe(EventListener listener) {
        if (!subscribers.contains(listener)) {
            subscribers.add(listener);
            listener.onSubscribed();
        }
    }

    @Override
    public void unsubscribe(EventListener listener) {
        if (subscribers.remove(listener)) {
            listener.onUnsubscribed();
        }
    }

    @Override
    public void publish(Events.GraphEvent event) {
        for (EventListener listener : subscribers) {
            if (listener.isInterestedIn(event)) {
                try {
                    listener.onEvent(event);
                } catch (Exception e) {
                    // Log error but continue publishing to other listeners
                    System.err.println("Error handling event in listener " + listener.listenerId() + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<EventListener> getSubscribers() {
        return Collections.unmodifiableList(new ArrayList<>(subscribers));
    }

    @Override
    public void clear() {
        for (EventListener listener : subscribers) {
            listener.onUnsubscribed();
        }
        subscribers.clear();
    }

    @Override
    public boolean hasSubscribers() {
        return !subscribers.isEmpty();
    }
}
