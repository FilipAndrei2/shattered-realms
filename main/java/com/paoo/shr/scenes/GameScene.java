package com.paoo.shr.scenes;

import com.paoo.shr.core.RequiresInitialization;
import com.paoo.shr.core.Window;
import com.paoo.shr.game.map.Map;
import com.paoo.shr.graphics.Drawable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public final class GameScene extends Scene implements RequiresInitialization, Drawable {

    private Pane root;

    private Canvas canvas;
    private GraphicsContext gc;

    private Map map;

    @Override
    public void init() {
        map.loadLevel1Map();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // clear canvas
        map.draw(gc);
    }

    @Override
    public void play() {

    }

    @Override
    protected void addComponent(Node component) {
        root.getChildren().add(component);
    }


    // Singleton
    private GameScene() {
        super(new Pane());

        this.map = new Map();
        map.loadLevel1Map();

        this.root = (Pane)this.getRoot();
        this.canvas = new Canvas(Window.WIDTH, Window.HEIGHT);
        this.gc = canvas.getGraphicsContext2D();

        gc.setFill(Paint.valueOf("green"));
//        gc.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
        this.draw(gc);
//        gc.drawImage(SpriteManager.getInstance().getTileSprite(4).getImage(), 60, 60, 100, 100);
        this.root.getChildren().add(canvas);
    }

    private static final class InstanceHolder { private static final GameScene INSTANCE = new GameScene(); }

    public static GameScene getInstance() { return InstanceHolder.INSTANCE; }
}
