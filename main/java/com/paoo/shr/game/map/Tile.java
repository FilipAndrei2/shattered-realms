package com.paoo.shr.game.map;

import org.jetbrains.annotations.NotNull;

public enum Tile {
    // Niste exemple, tb schimbate later.
    GRASS(1), WATER(2), SAND(3), STONE(4),
    A(5),
    B(6), C(7), D(8), E(9), F(10), G(11), H(12), I(13), J(14), K(15), L(16), M(17), N(18), O(19), P(20), Q(21), T22(22), T23(23),
    T24(24), T25(25), T26(26), T27(27), T28(28), T29(29), T30(30), T31(31), T32(32), T33(33), T34(34), T35(35), T36(36), T37(37), T38(38), T39(39),;

    public static final int SIZE = 16;
    public static final int OFFSET = 1;

    @NotNull
    public static Tile fromCode(int code) {
        for (Tile tile : Tile.values()) {
            if (tile.code == code) {
                return tile;
            }
        }
        throw new IllegalArgumentException("Invalid tile code: " + code);
    }

    public int getCode() {
        return code;
    }

    /**
     * Represents the number of the tile in the sprite sheet left to right, top to down.
     */
    final int  code;

    Tile(int code) {
        this.code = code;
    }
}
