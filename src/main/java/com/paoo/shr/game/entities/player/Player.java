package com.paoo.shr.game.entities.player;

import com.paoo.shr.engine.core.Window;
import com.paoo.shr.engine.graphics.SpriteManager;
import com.paoo.shr.game.entities.Actor;
import com.paoo.shr.game.entities.Attackable;
import com.paoo.shr.game.entities.Attacker;
import com.paoo.shr.game.entities.Moveable;
import com.paoo.shr.game.entities.collisions.Collidable;
import com.paoo.shr.game.entities.collisions.HitBox;
import com.paoo.shr.game.map.Map;
import com.paoo.shr.math.Compare;
import com.paoo.shr.math.Rng;
import com.paoo.shr.math.Vector2;
import com.paoo.shr.util.ToDo;
import com.paoo.shr.util.Validate;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;

import static com.paoo.shr.math.Vector2.*;

public final class Player extends Actor implements Moveable, Attackable, Attacker {


    // Add these fields
    private AnimationState currentState = AnimationState.IDLE;
    private int currentFrame = 0;
    private Timeline animationTimeline;
    private boolean facingRight = true; // Track direction player is facing
    

    /*
    ┌─────────────────────────────────────────────────────┐
    │   __  __                   _                        │
    │  |  \/  |  ___  _ __ ___  | |__    ___  _ __  ___   │
    │  | |\/| | / _ \| '_ ` _ \ | '_ \  / _ \| '__|/ __|  │
    │  | |  | ||  __/| | | | | || |_) ||  __/| |   \__ \  │
    │  |_|  |_| \___||_| |_| |_||_.__/  \___||_|   |___/  │
    └─────────────────────────────────────────────────────┘
    */
    public static final float PLAYER_HB_WIDTH = 190;
    public static final float PLAYER_HB_HEIGHT = 256;

    private float hp;
    private float maxHp;
    private float resistance;
    private float damage;
    private float vel;

    private boolean isAlive;
    SpriteManager sm = SpriteManager.getInstance();
    /*
    ┌───────────────────────────────────────────────────────────────────────────┐
    │    ____                    _                       _                      │
    │   / ___| ___   _ __   ___ | |_  _ __  _   _   ___ | |_  ___   _ __  ___   │
    │  | |    / _ \ | '_ \ / __|| __|| '__|| | | | / __|| __|/ _ \ | '__|/ __|  │
    │  | |___| (_) || | | |\__ \| |_ | |   | |_| || (__ | |_| (_) || |   \__ \  │
    │   \____|\___/ |_| |_||___/ \__||_|    \__,_| \___| \__|\___/ |_|   |___/  │
    └───────────────────────────────────────────────────────────────────────────┘
    */
    public Player(float maxHp, float resistance, float damage, Vector2 initialPos, int lvl) {
        super(lvl, initialPos, new Vector2(PLAYER_HB_WIDTH, PLAYER_HB_HEIGHT));

        this.maxHp = maxHp;
        this.resistance = resistance;
        this.damage = damage;
        this.hitBox = new HitBox(pos.getX(), pos.getY(), PLAYER_HB_WIDTH, PLAYER_HB_HEIGHT );

        this.vel = 3.1f;
        this.hp = maxHp;
        this.isAlive = true;
    }

    
    /*
    ┌───────────────────────────────────────────────────┐
    │     __  __        _    _                 _        │
    │    |  \/  |  ___ | |_ | |__    ___    __| | ___   │
    │    | |\/| | / _ \| __|| '_ \  / _ \  / _` |/ __|  │
    │    | |  | ||  __/| |_ | | | || (_) || (_| |\__ \  │
    │    |_|  |_| \___| \__||_| |_| \___/  \__,_||___/  │
    └───────────────────────────────────────────────────┘
    */
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

        // Calculate source rectangle for current frame
        double srcX = currentFrame * PLAYER_HB_WIDTH;
        double srcY = currentState.row * PLAYER_HB_HEIGHT;

        // Draw the current frame
        if (facingRight) {
            gc.drawImage(sm.getPlayerSpriteSheet(),
                srcX, srcY,
                PLAYER_HB_WIDTH, PLAYER_HB_HEIGHT,
                pos.getX(), pos.getY(),
                PLAYER_HB_WIDTH, PLAYER_HB_HEIGHT);
        } else {
            // Flip the image horizontally when facing left
            gc.save();
            gc.scale(-1, 1);
            gc.drawImage(sm.getPlayerSpriteSheet(),
                srcX, srcY,
                PLAYER_HB_WIDTH, PLAYER_HB_HEIGHT,
                -pos.getX() - PLAYER_HB_WIDTH, pos.getY(),
                PLAYER_HB_WIDTH, PLAYER_HB_HEIGHT);
            gc.restore();
        }
    }

    // Add these methods to control animations
    public void setAnimationState(AnimationState newState) {
        if (currentState != newState) {
            currentState = newState;
            currentFrame = 0; // Reset frame when changing animation
        }
    }

    @Override
    public void takeDamage(float damage) {
        hp -= damage / resistance;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void damage(Attackable a) {
        a.takeDamage(damage * Rng.randFloat(0.60f, 1.0f));
    }

    @Override
    public void setX(float x) {
        this.pos.setX(x);

        clamp();
        updateHitBox();
    }

    @Override
    public void setY(float y) {
        this.pos.setY(y);

        clamp();
        updateHitBox();
    }

    @Override
    public void moveBy(@NotNull Vector2 dir) {
        Validate.notNull(dir, "Player.moveBy(@NotNull Vector2 dir) : dir can't be null!");
        
        // Update facing direction based on movement
        if (dir.getX() != 0) {
            facingRight = dir.getX() > 0;
        }

        // Set animation state based on movement
        if (dir.getY() != 0) {
            setAnimationState(AnimationState.JUMPING);
        } else if (dir.getX() != 0) {
            setAnimationState(AnimationState.WALKING);
        } else {
            setAnimationState(AnimationState.IDLE);
        }

        Vector2 delta = Vector2.Mul(dir, vel);
        pos.add(delta);

        clamp();
        updateHitBox();
    }

    @Override
    public void moveTo(@NotNull Vector2 newPos) {
        Validate.notNull(newPos, "Player.moveTo(@NotNull Vector2 newPos) : newPos can't be null!");
        pos.setX(newPos.getX());
        pos.setY(newPos.getY());

        clamp();
        updateHitBox();
    }

    @Override
    public void moveTo(float x, float y) {
        pos.setX(x);
        pos.setY(y);

        clamp();
        updateHitBox();
    }

    @Override
    public HitBox getHitBox() {
        return this.hitBox;
    }

    @Override
    public boolean isColliding(Collidable other) {
        return this.hitBox.isColliding(other.getHitBox());
    }

    @Override
    public void update() {

    }

    // Add animation state enum
    public enum AnimationState {
        IDLE(0, 1, 0.5),    // Row 0, 1 frame, 0.5 seconds per frame
        WALKING(4, 3, 0.15), // Row 1, 4 frames, 0.15 seconds per frame
        JUMPING(0, 4, 0.3);  // Row 2, 2 frames, 0.3 seconds per frame

        final int row;
        final int frameCount;
        final double frameDuration;

        AnimationState(int row, int frameCount, double frameDuration) {
            this.row = row;
            this.frameCount = frameCount;
            this.frameDuration = frameDuration;
        }
    }

    private void clamp() {
        Map.clampPosition(this.pos, this.size);
    }

    // Add method to stop animation
    public void stopAnimation() {
        if (animationTimeline != null) {
            animationTimeline.stop();
            animationTimeline = null;
        }
    }

    /*
    ┌───────────────────────────────────────────┐
    │    ____        _    _                     │
    │   / ___|  ___ | |_ | |_  ___  _ __  ___   │
    │  | |  _  / _ \| __|| __|/ _ \| '__|/ __|  │
    │  | |_| ||  __/| |_ | |_|  __/| |   \__ \  │
    │   \____| \___| \__| \__|\___||_|   |___/  │
    └───────────────────────────────────────────┘
    */

    @Override
    public float getHp() {
        return hp;
    }

    @Override
    public float getMaxHp() {
        return maxHp;
    }

    public Vector2 getPos() {
        return new Vector2(pos);
    }

    public float getVelocity() {
        return vel;
    }


    /*
    ┌───────────────────────────────────────────┐
    │   ____         _    _                     │
    │  / ___|   ___ | |_ | |_  ___  _ __  ___   │
    │  \___ \  / _ \| __|| __|/ _ \| '__|/ __|  │
    │   ___) ||  __/| |_ | |_|  __/| |   \__ \  │
    │  |____/  \___| \__| \__|\___||_|   |___/  │
    └───────────────────────────────────────────┘
    */


}