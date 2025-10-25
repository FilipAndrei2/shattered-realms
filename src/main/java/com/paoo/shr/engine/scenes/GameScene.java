package com.paoo.shr.engine.scenes;

import com.paoo.shr.engine.core.RequiresInitialization;
import com.paoo.shr.engine.core.Window;
import com.paoo.shr.engine.input.CursorEvent;
import com.paoo.shr.engine.input.Input;
import com.paoo.shr.engine.input.KeyboardEvent;
import com.paoo.shr.engine.input.ScrollWheelEvent;
import com.paoo.shr.game.World;
import com.paoo.shr.math.Vector2;
import com.paoo.shr.util.designpatterns.observer.Listener;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashSet;
import java.util.Set;

import static javafx.scene.input.KeyCode.*;
import static javafx.scene.input.KeyEvent.*;

public class GameScene extends Scene implements RequiresInitialization {

    private Pane root;

    private Canvas canvas;
    private GraphicsContext gc;

    private boolean isRunning = false;

    private Vector2 playerDir = new Vector2();


    Set<KeyCode> pressedKeys = new HashSet<>();
//    Set<>

    private World world = new World();

    private Listener<KeyboardEvent> kbd = event -> {
        var type = event.getEventType();
        if (type == KEY_PRESSED) {
            pressedKeys.add(event.getCode());

        } else if (type == KEY_RELEASED) {
            pressedKeys.remove(event.getCode());
        }
    };

    private Listener<CursorEvent> mouse = event ->  { };

    private Listener<ScrollWheelEvent> scroll = event -> {
//        var type = event.getEventType();
//        if (type == SCROLL_)
    };

    @Override
    public void init() {
    }

    @Override
    public void play() {
        new GameLoop().start();
    }

    // In nanoseconds.
    private long last = 0;
    // In nanoseconds.
    private long deltaTimeNs = 0;

    private short targetTPS = 150;
    private long NANOSECONDS_BETWEEN_TICKS = 1_000_000_000 / targetTPS;
    private long lastTickNs = 0;

    private short targetFPS = 60;
    private long NANOSECONDS_BETWEEN_FRAMES = 1_000_000_000 / targetFPS;
    private long lastFrameNs = 0;

    private class GameLoop extends AnimationTimer {

        /**
         * @param now in nanoseconds.
         */
        @Override
        public void handle(long now) {

            if (!isRunning) {
                this.stop();
            }

            if (now - lastTickNs >= NANOSECONDS_BETWEEN_TICKS) {
                tick();
                lastTickNs = now;
            }

            if (now - lastFrameNs >= NANOSECONDS_BETWEEN_FRAMES) {
                render(gc);
                lastFrameNs = now;
            }
        }

    }

    private void tick() {
        movePlayer();
        updateCamera();
    }

    private void render(GraphicsContext gc) {

        gc.setFill(Color.WHITE);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // clear canvas

        gc.save();
        gc.scale(1, 1);
        world.draw(gc);
        gc.restore();
    }

    private void movePlayer() {
        playerDir.set(0, 0); // Reset player direction vector.
        if (pressedKeys.contains(W)) {
            playerDir.add(Vector2.north);
        }
        if (pressedKeys.contains(A)) {
            playerDir.add(Vector2.west);
        }
        if (pressedKeys.contains(S)) {
            playerDir.add(Vector2.south);
        }
        if (pressedKeys.contains(D)) {
            playerDir.add(Vector2.east);
        }
        playerDir.normalize(); // Pentru miscarea pe diagonala
        world.getPlayer().moveBy(playerDir);
    }

    private void updateCamera() {
        world.moveCamera(playerDir);
    }

    @Override
    protected void addComponent(Node component) {
        root.getChildren().add(component);
    }

    // Singleton

    private GameScene() {
        super(new Pane());

        this.isRunning = true;

        this.root = (Pane)this.getRoot();
        this.canvas = new Canvas(Window.WIDTH, Window.HEIGHT);
        this.gc = canvas.getGraphicsContext2D();

        gc.setFill(Paint.valueOf("green"));
//        gc.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
        this.render(gc);
//        gc.drawImage(SpriteManager.getInstance().getTileSprite(4).getImage(), 60, 60, 100, 100);
        this.root.getChildren().add(canvas);


        Input.registerToKeyboard(this.kbd);
        Input.registerToMouse(this.mouse);
        Input.registerToScroll(this.scroll);

    }

    private static final class InstanceHolder { private static final GameScene INSTANCE = new GameScene(); }

    public static GameScene getInstance() { return InstanceHolder.INSTANCE; }

}
