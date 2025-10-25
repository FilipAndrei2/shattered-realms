package com.paoo.shr.sound;

import com.paoo.shr.core.RequiresInitialization;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import kotlin.Pair;

import java.net.URL;


public final class SoundManager implements RequiresInitialization {

    AudioClip oof = null;

    public void playOof() { oof.play(); }

    @Override
    public void init() {
        URL url = this.getClass().getResource("/sounds/oof.mp3");
        System.out.println(url);
        oof = new AudioClip(url.toExternalForm());
    }

    // Singleton logic
    public static SoundManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static final class InstanceHolder { private  static final SoundManager INSTANCE = new SoundManager(); }
}
