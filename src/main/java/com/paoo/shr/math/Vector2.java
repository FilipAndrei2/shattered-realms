package com.paoo.shr.math;

import com.paoo.shr.util.Validate;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLOutput;
import java.util.Map;

/**
 * O clasa care reprezinta un vector bidimensional cu originea (punctul (0, 0)) in coltul stanga-sus al ecranului.
 */
public final class Vector2 {

    private float x;
    private float y;


    public Vector2() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Number x, Number y) {
        this.x = x.floatValue();
        this.y = y.floatValue();
    }

    public Vector2(final Vector2  other) {
        Validate.notNull(other, "Vector2.Vector2( Vector2 other) : other nu poate sa fie null!");

        this.x = other.x;
        this.y = other.y;
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }

    public Vector2 set(Vector2 other) {
        this.x = other.x;
        this.y = other.y;
        return this;
    }

    public Vector2 set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2 set(Number x, Number y) {
        this.x = x.floatValue();
        this.y = y.floatValue();
        return this;
    }

    // Mutable opps
    public @NotNull Vector2 normalize() {
        float length = length();
        if ( Compare.isZero(length)) { // DACA VALOAREA LUNGIMII E 0, VECTORUL E NULL SI DOAR RETURNEZ
            return this;
        }

        this.x /= length;
        this.y /= length;
        return this;
    }

    public Vector2 neg() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    public Vector2 add(@NotNull final Vector2 other){
        Validate.notNull(other, "Vector2.add(Vector2 other) : other nu poate sa fie null!");
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector2 sub(@NotNull final Vector2 other) {
        Validate.notNull(other, "Vector2.sub(Vector2 other) : other nu poate sa fie null!");
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Vector2 mul(final float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vector2 div(final float scalar) {
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

    public Vector2 clamp(float objWidth, float objHeight, float minX, float maxX, float minY, float maxY) {
        if (Compare.lessThan(this.x, minX)) {
            this.x = minX;
        } else if (Compare.greaterThan(this.x + objWidth, maxX)) {
            this.x = maxX - objWidth;
        }

        if (Compare.lessThan(this.y, minY)) {
            this.y = minY;
        } else if (Compare.greaterThan(this.y + objHeight, maxY)) {
            this.y = maxY - objHeight;
        }

        return this;
    }

    // Immutable opps

    public Vector2 normalized() {
        float length = length();
        if (Compare.lessThanEq(length, 0.0f)) { // DACA VALOAREA LUNGIMII E 0, VECTORUL E NULL SI DOAR RETURNEZ
            return new Vector2(this);
        }

        return new Vector2(x / length, y / length);
    }

    public float length() {
        if (Compare.isZero(this.x) || Compare.isZero(this.y)) {
            return 0.0f;
        }

        return ((float) Math.sqrt(getX() * getX() + getY() * getY())); // sqrt(x^2 + y^2)
    }

    public float dot(@NotNull Vector2 other) {
        Validate.notNull(other, "Vector2.dot(Vector2 other) : other nu poate sa fie null!");
        return this.x * other.x + this.y * other.y;
    }

    public Vector2 copy() {
        return new Vector2(this.x, this.y);
    }

    // Operatii matematice implementate static

    public static Vector2 Add(@NotNull Vector2 first, @NotNull Vector2 second) { return new Vector2(first.x + second.x, first.y + second.y); }
    public static Vector2 Sub(@NotNull Vector2 first,@NotNull Vector2 second) { return new Vector2(first.x - second.x, first.y - second.y); }
    public static Vector2 Mul(@NotNull Vector2 vect, float factor) { return new Vector2(vect.x * factor, vect.y * factor); }
    public static Vector2 Div(@NotNull Vector2 vect, float factor) {
        if (Float.compare(factor, 0.0f) == 0)  throw new ArithmeticException("Nu poti imparti la zero dummy! public static Vector2 Div(@NotNull Vector2 vect, float factor)");
        return new Vector2(vect.x / factor, vect.y / factor);
    }
    public static float Dot(@NotNull Vector2 first, @NotNull Vector2 second) { return first.x * second.x + first.y * second.y; }

    public final static Vector2 zero = new Vector2();

    public final static Vector2 north = new Vector2(0, -1);
    public final static Vector2 south = new Vector2(0, 1);
    public final static Vector2 west = new Vector2(-1, 0);
    public final static Vector2 east = new Vector2(1, 0);

    @Override
    public boolean equals(Object other) throws IllegalArgumentException {
        Validate.notNull(other, "Vector2.equals(Object other) : other nu poate sa fie null!");
        if (this == other) {
            return true;
        }

        if (other instanceof Vector2) {
            return ( Compare.equals(this.x, ((Vector2) other).x) && Compare.equals(this.y, ((Vector2) other).y) );
        }
        throw new IllegalArgumentException("public boolean equals(Object other): You can only compare a Vector2 object with another Vector2 object");
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }

    public Vector2 shallowCopy() {
        return new Vector2(this.x, this.y);
    }

    /**********************************************************************************************************************************************
                                      ______     ___       ______  __    __   _______
                                     /      |   /   \     /      ||  |  |  | |   ____|
                                    |  ,----'  /  ^  \   |  ,----'|  |__|  | |  |__
                                    |  |      /  /_\  \  |  |     |   __   | |   __|
                                    |  `----./  _____  \ |  `----.|  |  |  | |  |____
                                     \______/__/     \__\ \______||__|  |__| |_______|

     ********************************************************************************************************************************************/

    private Map<CacheId, Float> cache;

    private enum CacheId {
        SIN,
        COS,
        LENGTH;
    }

    private void add(CacheId key, float value) throws RuntimeException{
        if (cache.containsKey(key)) throw new RuntimeException("Exista deja o valoare pentru cheia " + key + "!");
        cache.put(key, value);
    }

    private void invalidate() {
        cache.clear();
    }
}