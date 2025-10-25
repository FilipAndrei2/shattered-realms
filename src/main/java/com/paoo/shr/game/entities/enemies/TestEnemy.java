package com.paoo.shr.game.entities.enemies;

import com.paoo.shr.engine.graphics.SpriteManager;
import com.paoo.shr.game.entities.Attackable;
import com.paoo.shr.game.entities.collisions.Collidable;
import com.paoo.shr.game.entities.collisions.HitBox;
import com.paoo.shr.math.Vector2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class TestEnemy extends Enemy {

    public static final Vector2 SIZE = new Vector2(300, 300);
    public static final float MAX_HP = 200.0f;

    public TestEnemy(int lvl, Vector2 pos) {
        super(lvl, pos, SIZE);

        this.hp = MAX_HP;
    }
    Timeline animationTimeline = null;
    AnimationState currentState = AnimationState.IDLE;
    private int currentFrame = 0;
    private boolean facingRight = true;
    private Image sprite = SpriteManager.getInstance().getZombieSpriteSheet();
    private Vector2 spriteSize = SpriteManager.getInstance().getSize(sprite);

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
    public void takeDamage(float damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
            this.isAlive = false;
        }
    }

    @Override
    public float getHp() {
        return this.hp;
    }

    @Override
    public float getMaxHp() {
        return MAX_HP;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void damage(Attackable a) {
        a.takeDamage(1.0f);
    }

    @Override
    public void setX(float x) {

    }

    @Override
    public void setY(float y) {

    }

    @Override
    public void moveBy(Vector2 dir) {

    }

    @Override
    public void moveTo(Vector2 newPos) {

    }

    @Override
    public void moveTo(float x, float y) {

    }

    @Override
    public void spawn(Vector2 spawnPos) {

    }

    @Override
    public void despawn() {

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

    private enum AnimationState {
        IDLE(3, 3, 0.5f);

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
