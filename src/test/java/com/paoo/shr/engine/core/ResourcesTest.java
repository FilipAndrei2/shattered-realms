package com.paoo.shr.engine.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ResourcesTest {

    @BeforeAll static void setup() {
        try {
            Resources.getInstance().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void retrievingResFolderTes() throws Exception {
        Class<?> clazz = Resources.class;
        Field resources;
        File file;

        resources = clazz.getDeclaredField("resources");
        resources.setAccessible(true);

        file = (File)resources.get(Resources.getInstance());
        assertTrue(file.exists() && file.isDirectory());
    }

    @Test
    void checkForExistingDirsInsideRes() throws Exception {
        Class<?> clazz = Resources.class;
        Field resources = clazz.getDeclaredField("folders");
        resources.setAccessible(true);
        File[] dirs = (File[])resources.get(Resources.getInstance());
        for (File dir : dirs) {
            assertTrue(dir.exists() && dir.isDirectory());
        }

    }
}