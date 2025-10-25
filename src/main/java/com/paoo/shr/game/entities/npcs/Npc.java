package com.paoo.shr.game.entities.npcs;

import com.paoo.shr.game.entities.Actor;
import com.paoo.shr.game.entities.Placeable;
import com.paoo.shr.math.Vector2;
import org.jetbrains.annotations.NotNull;

public abstract class Npc extends Actor implements Placeable {

    protected Npc(int lvl, @NotNull Vector2 size, @NotNull Vector2 pos) {
        super(lvl, pos, size);
    }

}
