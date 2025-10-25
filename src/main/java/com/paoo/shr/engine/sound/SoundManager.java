package com.paoo.shr.engine.sound;

import com.paoo.shr.engine.core.RequiresInitialization;
import com.paoo.shr.engine.core.Resources;
import javafx.scene.media.AudioClip;

import java.io.FileNotFoundException;
import java.net.URL;


public class SoundManager implements RequiresInitialization {

    AudioClip oof = null;

    public void playOof() {  }

    @Override
    public void init() {

    }

    // Singleton logic
    public static SoundManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static final class InstanceHolder { private  static final SoundManager INSTANCE = new SoundManager(); }
}
