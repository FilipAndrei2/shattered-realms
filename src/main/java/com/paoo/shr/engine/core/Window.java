package com.paoo.shr.engine.core;

import com.paoo.shr.engine.scenes.MainMenu;
import javafx.stage.Stage;
import com.paoo.shr.engine.scenes.SceneManager;

public class Window extends Stage {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final String TITLE = "Shattered Realms";


    private Window() {
        super();

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

    /*===================================================================================================

             _______. __  .__   __.   _______  __       _______ .___________.  ______   .__   __.
            /       ||  | |  \ |  |  /  _____||  |     |   ____||           | /  __  \  |  \ |  |
           |   (----`|  | |   \|  | |  |  __  |  |     |  |__   `---|  |----`|  |  |  | |   \|  |
            \   \    |  | |  . `  | |  | |_ | |  |     |   __|      |  |     |  |  |  | |  . `  |
        .----)   |   |  | |  |\   | |  |__| | |  `----.|  |____     |  |     |  `--'  | |  |\   |
        |_______/    |__| |__| \__|  \______| |_______||_______|    |__|      \______/  |__| \__|

    =====================================================================================================*/

    private static final class InstanceHolder { public static Window INSTANCE = new Window(); }

    public static Window getWindow(){ return InstanceHolder.INSTANCE; }
}
