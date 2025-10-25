package com.paoo.shr.graphics;

import com.paoo.shr.core.RequiresInitialization;
import com.paoo.shr.core.Resources;
import com.paoo.shr.game.map.Tile;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class SpriteManager implements RequiresInitialization {

    Image tileSheet = null;

    public static final int NUMBER_OF_TILES_ON_LENGHT = 56;
    public static final int NUMBER_OF_TILES_ON_HEIGHT = 31;

    /**
     * Tile size in pixels
     */
    public static final int TILE_SIZE = 32;

    /**
     * Offset between tiles in sprite sheet in pixels
     */
    public static final int TILE_OFFSET = 1;


    private Map<Integer, ImageView> tileCache = new HashMap<>();

    Map<Integer, ImageView> cache = new HashMap<>();

    private void loadTiles() {
        tileSheet = new Image(Resources.spr_tiles.getAbsolutePath());
        if (tileSheet == null) {
            throw new RuntimeException("Tile sheet could not be loaded");
        }

    }

    public void unloadTiles() {
        tileCache.clear();
        tileSheet = null;
        System.gc();
    }

    public Image getTilesSpriteSheet() {
        if (tileSheet == null) {
            loadTiles();
        }

        return tileSheet;
    }

    public int getTilePosX(Tile tile) {
        return (tile.getCode() % NUMBER_OF_TILES_ON_LENGHT) * (TILE_SIZE + TILE_OFFSET);
    }

    public int getTilePosY(Tile tile) {
        return (tile.getCode() / NUMBER_OF_TILES_ON_HEIGHT) * (TILE_SIZE + TILE_OFFSET);
    }

    // Singleton
    private SpriteManager() {
    }

    @Override
    public void init() throws Exception {
    }

    private static final class InstanceHolder {
        public static SpriteManager INSTANCE = new SpriteManager();
    }

    public static SpriteManager getInstance() {
        return InstanceHolder.INSTANCE;
    }
}