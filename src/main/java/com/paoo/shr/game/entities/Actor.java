package com.paoo.shr.game.entities;

import com.paoo.shr.game.entities.collisions.HitBox;
import com.paoo.shr.math.Vector2;
import com.paoo.shr.util.Validate;
import org.jetbrains.annotations.NotNull;

public abstract class Actor extends Entity implements Moveable {

    /*
    ┌─────────────────────────────────────────────────────┐
    │   __  __                   _                        │
    │  |  \/  |  ___  _ __ ___  | |__    ___  _ __  ___   │
    │  | |\/| | / _ \| '_ ` _ \ | '_ \  / _ \| '__|/ __|  │
    │  | |  | ||  __/| | | | | || |_) ||  __/| |   \__ \  │
    │  |_|  |_| \___||_| |_| |_||_.__/  \___||_|   |___/  │
    └─────────────────────────────────────────────────────┘
    */
    protected int lvl;
    protected boolean isAlive;
    /*
    ┌───────────────────────────────────────────────────────────────────────────┐
    │    ____                    _                       _                      │
    │   / ___| ___   _ __   ___ | |_  _ __  _   _   ___ | |_  ___   _ __  ___   │
    │  | |    / _ \ | '_ \ / __|| __|| '__|| | | | / __|| __|/ _ \ | '__|/ __|  │
    │  | |___| (_) || | | |\__ \| |_ | |   | |_| || (__ | |_| (_) || |   \__ \  │
    │   \____|\___/ |_| |_||___/ \__||_|    \__,_| \___| \__|\___/ |_|   |___/  │
    └───────────────────────────────────────────────────────────────────────────┘
    */
    protected Actor(int lvl, @NotNull Vector2 pos, @NotNull Vector2 size) {
        Validate.notNull(pos, "com.paoo.shr.game.entities.Actor.Actor : pos nu poate sa fie null");
        Validate.notNull(size, "com.paoo.shr.game.entities.Actor.Actor : size nu poate sa fie null");
        Validate.positionInMap(pos, size, "com.paoo.shr.game.entities.Actor.Actor : Incerci sa spawnezi un actor inafara hartii! ");

        this.lvl = lvl;
        this.pos = pos.copy();
        this.size = size.copy();
        this.hitBox = new HitBox(pos.getX(), pos.getY(), size.getX(), size.getY());
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

    /*
    ┌───────────────────────────────────────────┐
    │    ____        _    _                     │
    │   / ___|  ___ | |_ | |_  ___  _ __  ___   │
    │  | |  _  / _ \| __|| __|/ _ \| '__|/ __|  │
    │  | |_| ||  __/| |_ | |_|  __/| |   \__ \  │
    │   \____| \___| \__| \__|\___||_|   |___/  │
    └───────────────────────────────────────────┘
    */
    
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
