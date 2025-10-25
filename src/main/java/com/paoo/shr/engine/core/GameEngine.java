package com.paoo.shr.engine.core;


import com.paoo.shr.engine.graphics.SpriteManager;
import com.paoo.shr.engine.input.Input;
import com.paoo.shr.engine.sound.SoundManager;
import com.paoo.shr.engine.scenes.GameScene;
import com.paoo.shr.engine.scenes.MainMenu;
import com.paoo.shr.engine.scenes.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Objects;

import static com.paoo.shr.engine.scenes.SceneManager.SceneIds.MAIN_MENU;

public class GameEngine extends Application {


    /**
     * Entry point-ul programului.
     * @param stage Scena default oferita de fx, ignora.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        // Obtinere resurse
        Resources.getInstance().init();
        SpriteManager.getInstance().init();
        SoundManager.getInstance().init();

        // Initializari sisteme
        Window.getWindow().centerOnScreen();
        Input.getInstance().init();
        MainMenu.getInstance().init();
        GameScene.getInstance().init();

        SceneManager.getInstance().init();

        // Set initial scene to main menu
        SceneManager.getScene(MAIN_MENU).changeScene().play();
    }

    /**
     * Functia main
     * @param args
     */
    public static void main(String[] args) { launch(args); }
}
