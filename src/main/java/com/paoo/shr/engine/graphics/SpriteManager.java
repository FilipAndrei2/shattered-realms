package com.paoo.shr.engine.graphics;

import com.paoo.shr.engine.core.RequiresInitialization;
import com.paoo.shr.engine.core.Resources;
import com.paoo.shr.game.map.Tile;
import com.paoo.shr.math.Vector2;
import com.paoo.shr.util.Validate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Sistemul care se ocupa de loading-ul sprite-urilor.
 */
public final class SpriteManager implements RequiresInitialization {

    // Sprite sheeturile
    Image tileSheet = null;
    Image playerSpriteSheet = null;
    Image testNpcSpriteSheet = null;
    Image zombieSpriteSheet = null;

    public static final int NUMBER_OF_TILES_ON_LENGHT = 56;
    public static final int NUMBER_OF_TILES_ON_HEIGHT = 31;
    public static final int NUMBER_OF_TILES_ON_LENGHT_PLAYER = 9;
    public static final int NUMBER_OF_TILES_ON_HEIGHT_PLAYER = 4;

    /**
     * Tile size in pixels
     */
    public static final int TILE_SIZE = 16;

    /**
     * Offset between tiles in sprite sheet in pixels
     */
    public static final int TILE_OFFSET = 1;


    private Map<Integer, ImageView> tileCache = new HashMap<>();

    Map<Integer, ImageView> cache = new HashMap<>();
    Map<Image, Vector2> spriteSizes = new HashMap<>();

    /**
     * Incarca in memorie sprite sheet-ul cu tile-uri
     */
    private void loadTiles() {
        try {
            tileSheet = Resources.getImage("spr_tileSheet.png");
            spriteSizes.put(tileSheet, new Vector2(16.0f, 16.0f));
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException("Tile sheet could not be loaded");
        }
    }

    private void loadPlayerSpriteSheet() {
        try {
            playerSpriteSheet = Resources.getImage("spr_player.png");
            spriteSizes.put(playerSpriteSheet, new Vector2(190.0f, 256.0f));
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException("Player sprite sheet could not be loaded");
        }
    }

    private void loadTestNpcSpriteSheet() {
        try {
            testNpcSpriteSheet = Resources.getImage("spr_testNpc.png");
            spriteSizes.put(testNpcSpriteSheet, new Vector2(96.0f, 128.0f));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Test npc sprite sheet could not be loaded");
        }
    }

    public Vector2 getSize(Image img) {
        var size = spriteSizes.get(img);

        Validate.notNull(size, "com.paoo.shr.engine.graphics.SpriteManager.getSize : Imaginea nu este incarcata corect!");

        return size;
    }

    /**
     * Distruge sprite-ul cu tile-uri.
     */
    public void unloadTiles() {
        tileCache.clear();
        tileSheet = null;
        System.gc();
    }

    /**
     * Getter pentru sprite sheetul cu tile-uri.
     * @return Sprite sheet-ul cu dale.
     */
    public Image getTilesSpriteSheet() {
        if (tileSheet == null) {
            loadTiles();
        }

        return tileSheet;
    }

    public Image getPlayerSpriteSheet() {
        if (playerSpriteSheet == null) {
            loadPlayerSpriteSheet();
        }
        return playerSpriteSheet;
    }

    public Image getTestNpcSpriteSheet() {
        if (testNpcSpriteSheet == null) {
            loadTestNpcSpriteSheet();
        }
        return testNpcSpriteSheet;
    }

    public Image getZombieSpriteSheet() {
        if (zombieSpriteSheet == null) {
            try {
                zombieSpriteSheet = Resources.getImage("spr_zombie.png");
                spriteSizes.put(zombieSpriteSheet, new Vector2(96.0f, 128.0f));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return zombieSpriteSheet;
    }

    /**
     * Returneaza coordonata x din sprite sheet a dalei oferita ca parametru.
     * @param tile
     * @return Coordonata x din sprite sheet
     */
    public int getTilePosX(Tile tile) {
        return (tile.getCode() % NUMBER_OF_TILES_ON_LENGHT) * (TILE_SIZE + TILE_OFFSET);
    }

    public int getPlayerPosX(Tile tile) {
        return (tile.getCode() % NUMBER_OF_TILES_ON_LENGHT_PLAYER) * (TILE_SIZE + TILE_OFFSET);
    }
    /**
     * Returneaza coordonata y din sprite sheet a dalei oferita ca parametru.
     * @param tile
     * @return Coordonata y din sprite sheet
     */
    public int getTilePosY(Tile tile) {
        return (tile.getCode() / NUMBER_OF_TILES_ON_HEIGHT) * (TILE_SIZE + TILE_OFFSET);
    }

    public int getPlayerPosY(Tile tile) {
        return (tile.getCode() / NUMBER_OF_TILES_ON_HEIGHT_PLAYER) * (TILE_SIZE + TILE_OFFSET);
    }

    // Singleton
    private SpriteManager() { }

    @Override
    public void init() throws Exception { }

    private static final class InstanceHolder {
        public static SpriteManager INSTANCE = new SpriteManager();
    }

    public static SpriteManager getInstance() {
        return InstanceHolder.INSTANCE;
    }
}