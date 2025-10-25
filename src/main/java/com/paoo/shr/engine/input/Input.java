package com.paoo.shr.engine.input;

import com.paoo.shr.engine.core.RequiresInitialization;
import com.paoo.shr.engine.core.Window;
import com.paoo.shr.util.Validate;
import com.paoo.shr.util.designpatterns.observer.Listener;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;


public class Input implements RequiresInitialization {

    private KeyboardEventHandler kbdHandler = new KeyboardEventHandler();
    private MouseEventHandler mouseHandler = new MouseEventHandler();
    private ScrollWheelEventHandler scrollHandler= new ScrollWheelEventHandler();

    public static boolean registerToKeyboard(@NotNull Listener<KeyboardEvent> listener) {
        Validate.notNull(listener,  "Input.registerToKeyboard : keyboard listener can't be null");
        return getInstance().kbdHandler.registerListener(listener);
    }

    public static boolean removeFromKeyboard(Listener<KeyboardEvent> listener) {
        if (listener == null) {
            return false;
        }
        return getInstance().kbdHandler.removeListener(listener);
    }

    public static boolean registerToMouse(@NotNull Listener<CursorEvent> listener) {
        Validate.notNull(listener, "com.paoo.shr.engine.input.Input.registerToMouse : mouse listener can't be null!");
        return getInstance().mouseHandler.registerListener(listener);
    }

    public static boolean removeFromMouse(Listener<CursorEvent> listener) {
        if (listener == null) {
            return false;
        }
        return getInstance().mouseHandler.removeListener(listener);
    }

    public static boolean registerToScroll(@NotNull Listener<ScrollWheelEvent> listener) {
        Validate.notNull(listener, "com.paoo.shr.engine.input.Input.registerToScroll : listener can't be null");
        return getInstance().scrollHandler.registerListener(listener);
    }

    public static boolean removeFromScroll(Listener<ScrollWheelEvent> listener) {
        if (listener == null) {
            return false;
        }
        return getInstance().scrollHandler.removeListener(listener);
    }


    private void onKeyPress(@NotNull KeyEvent e) {
       kbdHandler.notifyListeners(new KeyboardEvent(e));
    }

    private void onKeyRelease(@NotNull KeyEvent e) {
        kbdHandler.notifyListeners(new KeyboardEvent(e));
    }

    private void onMousePressed(@NotNull MouseEvent e) {
        mouseHandler.notifyListeners(new CursorEvent(e));
    }

    private void onMouseReleased(@NotNull MouseEvent e) {
        mouseHandler.notifyListeners(new CursorEvent(e));
    }

    private void onMouseMove(@NotNull MouseEvent e) {
        mouseHandler.notifyListeners(new CursorEvent(e));
    }

    private void onMouseDrag(@NotNull MouseEvent e) {
        mouseHandler.notifyListeners(new CursorEvent(e));
    }

    private void onScroll(@NotNull ScrollEvent e) {
        scrollHandler.notifyListeners(new ScrollWheelEvent(e));
    }



    private Input() {

    }

    @Override
    public void init() throws Exception {
        Stage wnd = Window.getWindow();
        wnd.addEventHandler(KeyEvent.KEY_PRESSED, this::onKeyPress);
        wnd.addEventHandler(KeyEvent.KEY_RELEASED, this::onKeyRelease);
        wnd.addEventHandler(MouseEvent.MOUSE_PRESSED, this::onMousePressed);
        wnd.addEventHandler(MouseEvent.MOUSE_RELEASED, this::onMouseReleased);
        wnd.addEventHandler(MouseEvent.MOUSE_MOVED, this::onMouseMove);
        wnd.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::onMouseDrag);
        wnd.addEventHandler(ScrollEvent.SCROLL, this::onScroll);
    }

    private static final class InstanceHolder { private static final Input INSTANCE = new Input(); }

    public static Input getInstance() { return InstanceHolder.INSTANCE; }

}

class KeyboardEventHandler extends InputSubject<KeyboardEvent> {

}

class MouseEventHandler extends InputSubject<CursorEvent> {

}

class ScrollWheelEventHandler extends InputSubject<ScrollWheelEvent> {

}