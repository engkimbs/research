package com.engkimbs.coffee.common.event;

import com.engkimbs.coffee.common.event.api.EventStoreApi;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EventStoreHandler {

    private EventStoreApi eventStoreApi;

    public EventStoreHandler(EventStoreApi eventStoreApi) {
        this.eventStoreApi = eventStoreApi;
    }

    @EventListener(Event.class)
    @Async
    public void handle(Event event) {
        eventStoreApi.save(event);
    }
}
