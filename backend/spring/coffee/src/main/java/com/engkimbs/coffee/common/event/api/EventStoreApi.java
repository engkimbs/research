package com.engkimbs.coffee.common.event.api;

import com.engkimbs.coffee.common.event.Event;

public interface EventStoreApi {

    void save(Event event);
}
