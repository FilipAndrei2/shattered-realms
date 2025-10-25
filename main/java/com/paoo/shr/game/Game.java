package com.paoo.shr.game;


import com.paoo.shr.graphics.SpriteManager;
import com.paoo.shr.sound.SoundManager;
import com.paoo.shr.core.Window;
import com.paoo.shr.scenes.GameScene;
import com.paoo.shr.scenes.MainMenu;
import com.paoo.shr.scenes.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import static com.paoo.shr.scenes.SceneManager.SceneIds.*;

public final class Game extends Application {
    Window window =  Window.getWindow();

    @Override
    public void start(Stage stage) throws Exception {
        // Set initial scene to main menu
        SceneManager.getScene(MAIN_MENU).changeScene().play();
    }

    @Override
    public void init() throws Exception {
        super.init();

        // Obtinere resurse
        SpriteManager.getInstance().init();
        SoundManager.getInstance().init();

        // Initializari sisteme
        MainMenu.getInstance().init();
        GameScene.getInstance().init();
        SceneManager.getInstance().init();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) { launch(args); }
}
