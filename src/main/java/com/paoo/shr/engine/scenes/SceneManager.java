package com.paoo.shr.engine.scenes;

import com.paoo.shr.engine.core.RequiresInitialization;
import com.paoo.shr.engine.core.Window;

import java.util.HashMap;
import java.util.Map;

public class SceneManager implements RequiresInitialization {

    private static Map<SceneIds, Scene> scenes = new HashMap<>();
    private final Window managedWindow;

    public static boolean addScene(SceneIds sceneId,  Scene scene) throws IllegalArgumentException {
        if(scenes.containsKey(sceneId)) { // Daca deja exista o scena mapata la id-ul respectiv, arunca eroare
            throw new IllegalArgumentException("There is already a scene with the same id!");
        }
        return scenes.put(sceneId, scene) == null;
    }

    public static Scene getScene(SceneIds sceneId){
        if (scenes.containsKey(sceneId)) {
            return scenes.get(sceneId);
        }
        return null;
    }

    @Override
    public void init() {
        addScene(SceneIds.MAIN_MENU, MainMenu.getInstance());
        addScene(SceneIds.GAME, GameScene.getInstance());

        checkInitializationSuccess();
    }

    private void checkInitializationSuccess() {
        for (SceneIds sceneId : SceneIds.values()) {
            if (!scenes.containsKey(sceneId)) { throw new IllegalStateException("There is no scene mapped to " + sceneId + "!"); }
        }
    }

    public enum SceneIds {
        MAIN_MENU,
        GAME
    }

    private SceneManager(Window managedWindow) {
        this.managedWindow = managedWindow;
    }

    // Singleton logic
    public static SceneManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static final class InstanceHolder { private  static final SceneManager INSTANCE = new SceneManager(Window.getWindow()); }
}
