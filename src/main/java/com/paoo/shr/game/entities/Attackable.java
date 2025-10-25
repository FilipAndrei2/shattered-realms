package com.paoo.shr.game.entities;

public interface Attackable {
    void takeDamage(float damage);
    float getHp();
    float getMaxHp();
    boolean isAlive();
    default boolean isDead() { return !isAlive(); }
}
