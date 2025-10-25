package com.paoo.shr.game.camera;

import com.paoo.shr.engine.core.Window;
import com.paoo.shr.game.entities.Moveable;
import com.paoo.shr.game.map.Map;
import com.paoo.shr.math.Vector2;

public class Camera implements Moveable {


    private Vector2 camPos = new Vector2();
    private float camZoom = CAM_INITIAL_ZOOM;
    private float camSpeed = CAM_INITIAL_SPEED;
    private final float WIDTH = (float) Window.WIDTH;
    private final float HEIGHT = (float) Window.HEIGHT;

    public static final float CAM_INITIAL_ZOOM = 1.0f;
    public static final float CAM_INITIAL_SPEED = 1.0f;

    public Vector2 getCamPos() {
        return camPos.copy();
    }

    public float getCamZoom() {
        return camZoom;
    }

    public void setCamZoom(float camZoom) {
        this.camZoom = camZoom;
    }

    public float getCamSpeed() {
        return camSpeed;
    }

    public void setCamSpeed(float camSpeed) {
        this.camSpeed = camSpeed;
    }

    @Override
    public void setX(float x) {
        camPos.setX(x);
        this.clamp();
    }

    @Override
    public void setY(float y) {
        camPos.setY(y);
        this.clamp();
    }

    @Override
    public void moveBy(Vector2 dir) {
        Vector2 delta = Vector2.Mul(dir, camSpeed);
        camPos.add(delta);

        this.clamp();
    }

    @Override
    public void moveTo(Vector2 newPos) {
        camPos = newPos.copy();
        this.clamp();
    }

    @Override
    public void moveTo(float x, float y) {
        camPos.set(x, y);
        this.clamp();
    }

    private void clamp() {
       camPos.clamp(WIDTH * camZoom, HEIGHT * camZoom, Map.MIN_X, Map.MAX_X, Map.MIN_Y, Map.MAX_Y);
    }
}

