package com.paoo.shr.game.map;

import com.paoo.shr.engine.core.Resources;
import com.paoo.shr.engine.core.Window;
import com.paoo.shr.engine.graphics.Drawable;
import com.paoo.shr.engine.graphics.SpriteManager;
import com.paoo.shr.math.Vector2;
import com.paoo.shr.util.Validate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Map implements Drawable {

    SpriteManager sm = SpriteManager.getInstance();

    /**
     * Nr de dale pe lungime.
     */
    public static final int WIDTH = 90;
    /**
     * Nr de dale pe inaltime
     */
    public static final int HEIGHT = 65;

    public static final float MIN_X = 0.0f;
    public static final float MAX_X = (float) WIDTH * SpriteManager.TILE_SIZE;
    public static final float MIN_Y = 0.0f;
    public static final float MAX_Y = (float) HEIGHT * SpriteManager.TILE_SIZE;

    /**
     * Array-ul in care se stocheaza reprezentarea interna a hartii.
     */
    private Tile[][] tiles = new Tile[WIDTH][HEIGHT];

    /**
     * Indica ce nivel este incarcat
     * 0   : neinitializat
     * 1-3 : levels 1-3
     */
    private int levelLoaded = 0; // Initially, it takes zero to indicate that no map is loaded.

    private boolean isCreated = false;

    /**
     * Aloca memoria necesara pentru array-ul de tile-uri.
     */
    private void createTileGrid() {
        if (Objects.isNull(tiles)) {
            tiles = new Tile[WIDTH][HEIGHT];
        }

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                tiles[x][y] = null;
            }
        }
        isCreated = true;
        levelLoaded = 0;
    }

    /**
     * Incarca harta cu nivelul 1 din fisierul resources.
     */
    public void loadLevel1Map()  {
        if (this.tiles == null) { createTileGrid(); }

        Scanner scn = null;
        try {
            scn = new Scanner(Resources.getTextFile("map1.txt"));

            for (int i = 0; i < WIDTH; ++i) {
                for (int j = 0; j < HEIGHT; ++j) {
                    int type = scn.nextInt();
                    tiles[i][j] = Tile.fromCode(type);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error loading level 1 map" + e.getStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Eroare la incarcarea nivelului 1");
        } finally {
            scn.close();
        }


        levelLoaded = 1;
    }

    public void loadLevel2Map() {
        if (!isCreated) { createTileGrid(); }
        throw new NotImplementedError();
    }

    public void loadLevel3Map() {
        if (!isCreated) { createTileGrid(); }
        throw new NotImplementedError();
    }

    /**
     * Randeaza harta pe GraphicsContext
     * @param gc
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Paint.valueOf("green"));
        gc.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Tile tile = tiles[x][y];
                gc.drawImage(sm.getTilesSpriteSheet(),
                        sm.getTilePosX(tile),       // src x
                        sm.getTilePosY(tile),       // src y
                        SpriteManager.TILE_SIZE,    // src w
                        SpriteManager.TILE_SIZE,    // src h
                        x * SpriteManager.TILE_SIZE,// dst x
                        y * SpriteManager.TILE_SIZE,// dst y
                        SpriteManager.TILE_SIZE,    // dst w
                        SpriteManager.TILE_SIZE     // dst h
                );
            }
        }
    }

    /**
     * Afiseaza la consola reprezentarea interna a hartii.
     */
    public void printMap() { System.out.println(this.toString()); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < WIDTH; ++i) {
            for (int j = 0; j < HEIGHT; ++j) {
                sb.append(tiles[i][j].toString() + " ");
            }
            sb.append("\b\n");
        }
        return sb.toString();
    }

    public static void clampPosition(@NotNull Vector2 pos, @NotNull final Vector2 size) {
        Validate.notNull(pos, "com.paoo.shr.game.map.Map.clampPosition : pos nu poate sa fie null");
        Validate.notNull(size, "com.paoo.shr.game.map.Map.clampPosition : size nu poate sa fie null");

        pos.clamp(size.getX(), size.getY(), Map.MIN_X, Map.MAX_X, Map.MIN_Y, Map.MAX_Y);
    }

    public static float widthPixels() {
        return WIDTH * SpriteManager.TILE_SIZE;
    }

    public static float heightPixels() {
        return HEIGHT * SpriteManager.TILE_SIZE;
    }
}
