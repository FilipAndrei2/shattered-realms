package com.paoo.shr.game.entities.collisions;

public interface Collidable {
    HitBox getHitBox();
    boolean isColliding(Collidable other);
}
