package com.paoo.shr.engine.input;

import javafx.scene.input.ScrollEvent;

public class ScrollWheelEvent implements InputEvent {

    ScrollEvent scrollEvent;

    public javafx.event.EventType<ScrollEvent> getEventType() {
        return this.scrollEvent.getEventType();
    }

    public ScrollWheelEvent(ScrollEvent scrollEvent) {
        this.scrollEvent = scrollEvent;
    }
}
