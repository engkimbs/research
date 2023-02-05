package com.engkimbs.coffee.common.event.infra;

import com.engkimbs.coffee.common.event.Event;
import com.engkimbs.coffee.common.event.api.EventStoreApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MockDataPlatformEventStoreApi implements EventStoreApi {

    @Override
    public void save(Event event) {
        log.info("event saved " + event.toString());
    }
}