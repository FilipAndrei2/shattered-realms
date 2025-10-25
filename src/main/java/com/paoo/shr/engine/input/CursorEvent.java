package com.paoo.shr.engine.input;


import javafx.scene.input.MouseEvent;

public class CursorEvent implements InputEvent {
    public float x, y;

    public CursorEvent(MouseEvent mouseEvent) {
        this.x = (float) mouseEvent.getSceneX();
        this.y = (float) mouseEvent.getSceneY();
    }
}
