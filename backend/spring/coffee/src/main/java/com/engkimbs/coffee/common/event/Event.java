package com.engkimbs.coffee.common.event;

public abstract class Event {

    private long timestamp;

    public Event() {
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }

}
