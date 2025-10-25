package com.paoo.shr.math;

import com.paoo.shr.game.entities.Moveable;

public interface Shape extends Moveable {
    float area();
    float perimeter();

    float getX();
    float getY();
    Vector2 getPosition();

}
