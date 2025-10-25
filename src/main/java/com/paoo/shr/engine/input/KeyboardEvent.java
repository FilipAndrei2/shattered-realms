package com.paoo.shr.engine.input;

import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardEvent implements InputEvent {
    private final EventType<KeyEvent> eventType;

    private KeyCode keyCode;
    public KeyboardEvent(KeyEvent keyEvent) {
        this.keyCode = keyEvent.getCode();
        this.eventType = keyEvent.getEventType();
    }
    public KeyCode getCode() {
        return this.keyCode;
    }

    public EventType<KeyEvent> getEventType() {
        return eventType;
    }
}
