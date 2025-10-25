package com.paoo.shr.core;

import java.io.File;

public final class Resources {
    // Text files
    public static final File txt_map1   = new File(Resources.class.getResource( "/maps/map1.txt").toExternalForm());
    public static final File txt_map2   = new File( Resources.class.getResource("/maps/map2.txt").toExternalForm());
    public static final File txt_map3   = new File(Resources.class.getResource( "/maps/map3.txt").toExternalForm());

    // Sprites
    public static final File spr_tiles  = new File(Resources.class.getResource("/sprites/tiles.png").toExternalForm());
    public static final File spr_bckgr  = new File(Resources.class.getResource("/sprites/back.png").toExternalForm());
    public static final File spr_btns   = new File(Resources.class.getResource("/sprites/NewB.png").toExternalForm());

    // Audio files
    public static final File snd_oof    = new File(Resources.class.getResource("/sounds/oof.mp3").toExternalForm());

}
