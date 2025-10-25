package com.paoo.shr.util.designpatterns.observer;

public interface Listener<E extends Event> {
    void update(E event);
}
