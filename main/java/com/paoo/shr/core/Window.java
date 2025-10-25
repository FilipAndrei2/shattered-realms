package com.paoo.shr.core;

import com.paoo.shr.scenes.MainMenu;
import javafx.stage.Stage;
import com.paoo.shr.scenes.SceneManager;

public final class Window extends Stage {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Shattered Realms";


    public Window() {
        super();

        System.out.println("Window");

        this.setTitle(TITLE);
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setResizable(false);
        this.setFullScreen(false);
        this.setScene(MainMenu.getInstance());

        this.show();
    }

    public void changeScene(SceneManager.SceneIds sceneId){
        this.setScene(SceneManager.getScene(sceneId));
    }

    // Singleton
    private static final class InstanceHolder { public static Window INSTANCE = new Window(); }

    public static Window getWindow(){ return InstanceHolder.INSTANCE; }
}
