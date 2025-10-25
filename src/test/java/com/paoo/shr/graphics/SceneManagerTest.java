package com.paoo.shr.graphics;

import org.junit.jupiter.api.Test;
import com.paoo.shr.engine.scenes.SceneManager;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class SceneManagerTest {

    @Test
    void ScenesShouldNotHavePublicConstructors() {
        Class<?> clazz = SceneManager.class;
        Constructor<?>[] ctors = clazz.getConstructors();

        assertEquals(ctors.length, 0);
    }

    @Test
    void ScenesShouldReturnValidInstance() {
        SceneManager instance = SceneManager.getInstance();
    }
}