package com.paoo.shr.engine.input;

import com.paoo.shr.util.designpatterns.observer.Listener;
import com.paoo.shr.util.designpatterns.observer.Subject;

import java.util.HashSet;
import java.util.Set;

public class InputSubject<E extends InputEvent> implements Subject<E> {

    private Set<Listener<E>> listeners = new HashSet<>();

    @Override
    public boolean registerListener(Listener<E> listener) {
        return listeners.add(listener);
    }

    @Override
    public boolean removeListener(Listener<E> listener) {
        return listeners.remove(listener);
    }

    @Override
    public void notifyListeners(E event) {
        for (Listener<E> listener : listeners) {
            listener.update(event);
        }
    }
}
