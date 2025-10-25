package com.paoo.shr.util;

import com.paoo.shr.game.map.Map;
import com.paoo.shr.math.Compare;
import com.paoo.shr.math.Vector2;
import org.jetbrains.annotations.NotNull;

public final class Validate {
    public static <T> T notNull(T obj, String msg) {
        if (obj == null) throw new IllegalArgumentException(msg);
        return obj;
    }

    public static void positionInMap(@NotNull Vector2 pos, @NotNull Vector2 size, String msg) {
        float x = pos.getX(), y = pos.getY();
        if (Compare.lessThan(x, Map.MIN_X)) {
            throw new RuntimeException(msg + '\n' + "Obiectul se afla in vestul hartii!! Pozitia obiectului: (" + x + ", " + y + ")");
        } else if (Compare.greaterThan(x + size.getX(), Map.MAX_X)) {
            throw new RuntimeException(msg + '\n' + "Obiectul se afla in estul hartii!! Pozitia obiectului: (" + x + ", " + y + ")");
        }

        if (Compare.lessThan(y, Map.MIN_Y)) {
            throw new RuntimeException(msg + '\n' + "Obiectul se afla in nordul hartii!! Pozitia obiectului: (" + x + ", " + y + ")");
        } else if (Compare.greaterThan(y + size.getY(), Map.MAX_Y)) {
            throw new RuntimeException(msg + '\n' + "Obiectul se afla in sudul hartii!! Pozitia obiectului: (" + x + ", " + y + ")");
        }
    }

}
