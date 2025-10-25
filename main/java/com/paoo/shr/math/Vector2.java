package com.paoo.shr.math;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

public final class Vector2 {

    public static enum Id {
        SIN,
        COS,
        LENGTH;
    }

    public void add(Id key, float value) throws RuntimeException{
        if (cache.containsKey(key)) throw new RuntimeException("Exista deja o valoare pentru cheia " + key + "!");
        cache.put(key, value);
    }

    public void invalidate() {
        cache.clear();
    }


    private float x;
    private float y;
    public Map<Id, Float> cache;


    public Vector2() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(final Vector2  other) throws  IllegalArgumentException{
        if (other == null)  {
            throw new IllegalArgumentException("AI DAT NULL PENTRU COPY CONSTRUCTORUL! public Vector2(final Vector2  other) ");
        }

        this.x = other.x;
        this.y = other.y;
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }

    public float length(){

        return ((float) Math.sqrt(getX() * getX() + getY() * getY())); // sqrt(x^2 + y^2)
    }

    public void normalize() {
        float length = length();
        if ( Float.compare(length, 0.0f) <= 0) { // DACA VALOAREA LUNGIMII E 0, VECTORUL E NULL SI DOAR RETURNEZ
            return;
        }

        this.x /= length;
        this.y /= length;
    }

    public Vector2 normalized() {
        float length = length();
        if ( Float.compare(length, 0.0f) <= 0) { // DACA VALOAREA LUNGIMII E 0, VECTORUL E NULL SI DOAR RETURNEZ
            return new Vector2(this);
        }

        return new Vector2(x / length, y / length);
    }

    // Operatii matematice implementate static

    public static @NotNull Vector2 Add(@NotNull Vector2 first, @NotNull Vector2 second) { return new Vector2(first.x + second.x, first.y + second.y); }
    public static Vector2 Sub(@NotNull Vector2 first,@NotNull Vector2 second) { return new Vector2(first.x - second.x, first.y - second.y); }
    public static Vector2 Mul(@NotNull Vector2 vect, float factor) { return new Vector2(vect.x * factor, vect.y * factor); }
    public static Vector2 Div(@NotNull Vector2 vect, float factor) {
        if (Float.compare(factor, 0.0f) == 0)  throw new ArithmeticException("Nu poti imparti la zero dummy! public static Vector2 Div(@NotNull Vector2 vect, float factor)");
        return new Vector2(vect.x / factor, vect.y / factor);
    }
    public static float Dot(@NotNull Vector2 first, @NotNull Vector2 second) { return first.x * second.x + first.y * second.y; }


    @Override
    public boolean equals(Object other) throws IllegalArgumentException {

        if (other instanceof Vector2) {
            return ( (Float.compare(this.x, ((Vector2) other).x) == 0) && (Float.compare(this.y, ((Vector2) other).y) == 0) );
        }
        throw new IllegalArgumentException("public boolean equals(Object other): You can only compare a Vector2 object with another Vector2 object");

    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}