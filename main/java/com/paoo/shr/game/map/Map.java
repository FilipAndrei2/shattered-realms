package com.paoo.shr.game.map;

import com.paoo.shr.core.Resources;
import com.paoo.shr.core.Window;
import com.paoo.shr.graphics.Drawable;
import com.paoo.shr.graphics.SpriteManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import kotlin.NotImplementedError;

import javax.imageio.IIOException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public final class Map implements Drawable {

    SpriteManager sm = SpriteManager.getInstance();

    /**
     * No of tiles on width
     */
    public static final int WIDTH = 35;
    /**
     * No of tiles on height
     */
    public static final int HEIGHT = 10;

    private Tile[][] tiles = new Tile[WIDTH][HEIGHT];

    /**
     * Indicates what map is loaded from resources
     * 0   : uninitialized
     * 1-3 : levels 1-3
     */
    private int levelLoaded = 0; // Initially, it takes zero to indicate that no map is loaded

    private boolean isCreated = false;

    private void createTileGrid() {
        if (Objects.isNull(tiles)) {
            tiles = new Tile[WIDTH][HEIGHT];
        }

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                tiles[x][y] = Tile.D;
            }
        }
        isCreated = true;
        levelLoaded = 0;
    }

    public void loadLevel1Map() {
        if (this.tiles == null) { createTileGrid(); }

//        try {
            Scanner scn = new Scanner(Resources.txt_map1.getAbsolutePath());
            for (int i = 0; i < WIDTH; ++i) {
                for (int j = 0; j < HEIGHT; ++j) {
                    int type = scn.nextInt();
                    System.out.println(type+" ");
                    tiles[i][j] = Tile.fromCode(type);
                }
            }
            scn.close();
//        } catch (FileNotFoundException fnfe) {
//            System.err.println("Error! File " + Resources.txt_map1 + " doesn't exist!");
//            fnfe.printStackTrace();
//        } catch (IOException ioe) {
//            System.err.println("Error! File " + Resources.txt_map1 + " doesn't exist!");
//            ioe.printStackTrace();
//        }

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

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Paint.valueOf("green"));
        gc.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Tile tile = tiles[x][y];
                System.out.println("Incerc sa desenez un tile la pozitia (x = " + x + ", y = " + y + ")!\nSprite-ul are pozitia (" + sm.getTilePosX(tile) + ", " + sm.getTilePosY(tile) + ")");
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
}
