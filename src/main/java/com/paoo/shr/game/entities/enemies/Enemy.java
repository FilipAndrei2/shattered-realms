package com.paoo.shr.game.entities.enemies;

import com.paoo.shr.game.entities.Actor;
import com.paoo.shr.game.entities.Attackable;
import com.paoo.shr.game.entities.Attacker;
import com.paoo.shr.game.entities.Placeable;
import com.paoo.shr.math.Vector2;
import org.jetbrains.annotations.NotNull;

public abstract class Enemy extends Actor implements Placeable, Attackable, Attacker {

    protected float hp = 0.0f;

    protected Enemy(int lvl, @NotNull Vector2 pos, @NotNull Vector2 size) {
        super(lvl, pos, size);
    }
}
