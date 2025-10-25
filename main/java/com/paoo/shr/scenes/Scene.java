package com.paoo.shr.scenes;

import com.paoo.shr.core.Window;
import javafx.scene.Node;
import javafx.scene.Parent;

public abstract class Scene extends javafx.scene.Scene {

    public Scene changeScene() {
        Window.getWindow().setScene(this);
        return this;
    }

    public abstract void play();

    public Scene(Parent parent) {
        super(parent);
    }

    protected abstract void addComponent(Node component);

}
