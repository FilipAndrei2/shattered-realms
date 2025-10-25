package com.paoo.shr.game.entities;

import com.paoo.shr.math.Vector2;

public interface Moveable {

    void setX(float x);
    void setY(float y);

    void moveBy(Vector2 dir);
    void moveTo(Vector2 newPos);
    void moveTo(float x, float y);
}
