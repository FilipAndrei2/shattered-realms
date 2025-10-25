package com.paoo.shr.game.entities;

import com.paoo.shr.math.Vector2;

public interface Placeable {
    void spawn(Vector2 spawnPos);
    void despawn();
}