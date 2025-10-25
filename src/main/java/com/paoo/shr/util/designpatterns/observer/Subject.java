package com.paoo.shr.util.designpatterns.observer;

public interface Subject<E extends Event> {
    boolean registerListener(Listener<E> listener);
    boolean removeListener(Listener<E> listener);
    void notifyListeners(E event);
}
