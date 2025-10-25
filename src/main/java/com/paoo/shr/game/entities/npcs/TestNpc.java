package com.paoo.shr.game.entities.npcs;

import com.paoo.shr.engine.graphics.SpriteManager;
import com.paoo.shr.game.entities.collisions.Collidable;
import com.paoo.shr.game.entities.collisions.HitBox;
import com.paoo.shr.game.map.Map;
import com.paoo.shr.math.Vector2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

public class TestNpc extends Npc {

    public static final Vector2 SIZE = new Vector2(100, 128);
    private AnimationState currentState = AnimationState.IDLE;
    private Timeline animationTimeline = null;
    private int currentFrame = 0;
    private boolean facingRight = true;
    private float vel = 0.0f;
    private boolean isAlive = false;

    private static final Image sprite = SpriteManager.getInstance().getTestNpcSpriteSheet();
    private static final Vector2 spriteSize = SpriteManager.getInstance().getSize(sprite);

    public TestNpc(int lvl, @NotNull Vector2 pos) {
        super(lvl, SIZE, pos);
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (animationTimeline == null ||
                animationTimeline.getKeyFrames().get(0).getTime().toSeconds() != currentState.frameDuration) {
            // Create or update timeline with current animation state
            if (animationTimeline != null) {
                animationTimeline.stop();
            }
            animationTimeline = new Timeline(new KeyFrame(
                    Duration.seconds(currentState.frameDuration),
                    event -> currentFrame = (currentFrame + 1) % currentState.frameCount
            ));
            animationTimeline.setCycleCount(Timeline.INDEFINITE);
            animationTimeline.play();
        }

        double srcX = currentFrame * spriteSize.getX();
        double srcY = currentState.row * spriteSize.getY();

        // Draw the current frame
        if (facingRight) {
            gc.drawImage(sprite,
                    srcX, srcY,
                    spriteSize.getX(), spriteSize.getY(),
                    pos.getX(), pos.getY(),
                    size.getX(), size.getY());
        } else {
            // Flip the image horizontally when facing left
            gc.save();
            gc.scale(-1, 1);
            gc.drawImage(sprite,
                    srcX, srcY,
                    spriteSize.getX(), spriteSize.getY(),
                    pos.getX(), pos.getY(),
                    size.getX(), size.getY());
            gc.restore();
        }
    }

    @Override
    public void setX(float x) {
        pos.setX(x);
        Map.clampPosition(this.pos, this.size);
    }

    @Override
    public void setY(float y) {
        pos.setY(y);
        Map.clampPosition(this.pos, this.size);
    }

    @Override
    public void moveBy(Vector2 dir) {
        pos.add(dir.mul(vel));
    }

    @Override
    public void moveTo(Vector2 newPos) {
        pos = newPos.copy();
        Map.clampPosition(pos, size);
    }

    @Override
    public void moveTo(float x, float y) {
        pos.set(x, y);
        Map.clampPosition(pos, size);
    }

    @Override
    public void spawn(Vector2 spawnPos) {
        this.pos = spawnPos.copy();
        Map.clampPosition(pos, size);
        isAlive = true;
    }

    @Override
    public void despawn() {
        isAlive = false;
    }

    @Override
    public HitBox getHitBox() {
        return this.hitBox;
    }

    @Override
    public boolean isColliding(Collidable other) {
        return false;
    }

    @Override
    public void update() {

    }

    public enum AnimationState {

        IDLE(0, 5, 0.5);

        final int row;
        final int frameCount;
        final double frameDuration;

        AnimationState(int row, int frameCount, double frameDuration) {
            this.row = row;
            this.frameCount = frameCount;
            this.frameDuration = frameDuration;
        }
    }
}
