package com.paoo.shr.game.entities.collisions;

import com.paoo.shr.math.Rect;
import com.paoo.shr.math.Shape;
import com.paoo.shr.math.Vector2;
import com.paoo.shr.util.Validate;
import org.jetbrains.annotations.NotNull;

public class HitBox {

    /*
    ┌─────────────────────────────────────────────────────┐
    │   __  __                   _                        │
    │  |  \/  |  ___  _ __ ___  | |__    ___  _ __  ___   │
    │  | |\/| | / _ \| '_ ` _ \ | '_ \  / _ \| '__|/ __|  │
    │  | |  | ||  __/| | | | | || |_) ||  __/| |   \__ \  │
    │  |_|  |_| \___||_| |_| |_||_.__/  \___||_|   |___/  │
    └─────────────────────────────────────────────────────┘
    */
    protected Rect shape;

    /*
    ┌───────────────────────────────────────────────────────────────────────────┐
    │    ____                    _                       _                      │
    │   / ___| ___   _ __   ___ | |_  _ __  _   _   ___ | |_  ___   _ __  ___   │
    │  | |    / _ \ | '_ \ / __|| __|| '__|| | | | / __|| __|/ _ \ | '__|/ __|  │
    │  | |___| (_) || | | |\__ \| |_ | |   | |_| || (__ | |_| (_) || |   \__ \  │
    │   \____|\___/ |_| |_||___/ \__||_|    \__,_| \___| \__|\___/ |_|   |___/  │
    └───────────────────────────────────────────────────────────────────────────┘
    */
    public HitBox(float x, float y, float w, float h) {
        shape = new Rect(x, y, w, h);
    }

    public HitBox(@NotNull Rect s) {
        Validate.notNull(s, "HitBox.HitBox(@NotNull Rect s)  : s nu poate sa fie null!");
        this.shape = s;
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
    public void moveTo(float x, float y) {
        shape.moveTo(x, y);
    }

    public void moveTo(Vector2 pos) {
        shape.moveTo(pos);
    }

    public void moveBy(Vector2 dir) {
        shape.moveBy(dir);
    }

    public void setX(float x) {
        shape.setX(x);
    }

    public void setY(float y) {
        shape.setY(y);
    }

    public void changeShape(@NotNull Rect newShape) {
        Validate.notNull(newShape, "HitBox.changeShape(@NotNull Rect newShape) : newShape nu poate sa fie null!");
        this.shape = newShape;
    }

    public boolean isColliding(@NotNull HitBox other) {
        Validate.notNull(other, "HitBox.isColliding(@NotNull HitBox other) : other nu poate sa fie null! ");
        return shape.collides(other.shape);
    }
}
