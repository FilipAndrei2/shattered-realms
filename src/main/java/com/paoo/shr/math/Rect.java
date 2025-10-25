package com.paoo.shr.math;

import com.paoo.shr.util.ToDo;

import static com.paoo.shr.math.Compare.*;

public class Rect implements Shape{

    private float x, y, w, h;

    public Rect(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Rect(float x, float y) {
        this.x = x;
        this.y = y;
        this.w = 0.0f;
        this.h = 0.0f;
    }

    public Rect() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.w = 0.0f;
        this.h = 0.0f;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Vector2 getPosition() {
        return new Vector2(x, y);
    }

    public float getWidth() {
        return w;
    }

    public float getHeight() {
        return h;
    }

    public Vector2 getSize() {
        return new Vector2(w, h);
    }

    public void setX(float x) {
        this.x = x;

    }
    public void setY(float y) {
        this.y = y;
    }

    public void moveTo(Vector2 newPos){
        this.x = newPos.getX();
        this.y = newPos.getX();
    }

    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void moveBy(Vector2 pos) {
        this.x = pos.getX();
        this.y = pos.getY();
    }

    public void setWidth(float w) {
        this.w = w;
    }

    public void setHeight(float h) {
        this.h = h;
    }

    public void setSize(float w, float h) {
        this.w = w;
        this.h = h;
    }

    public boolean isEmpty() {
        return isZero(x) || isZero(y);
    }

    public boolean hasSize() {
        return !isZero(x) && !isZero(y);
    }

    public boolean intersects(Rect other) {
        return lessThan(this.x, other.x + other.w) &&
                greaterThan(this.x + this.w, other.x) &&
                lessThan(this.y, other.y + other.h) &&
                greaterThan(this.y + this.h, other.y);
    }

    public boolean contains(float x, float y) {
//        return (this.x <= x) &&
//                (this.x + this.w >= x) &&
//                (this.y <= y) &&
//                (this.y + this.h >= y);
        return lessThanEq(this.x, x) &&
                greaterThanEq(this.x + this.w, x) &&
                lessThanEq(this.y, y) &&
                greaterThanEq(this.y + this.h, y);
    }

    public boolean contains(Vector2 pos) {
        return contains(pos.getX(), pos.getY());
    }

    public boolean contains(Rect other) {

        return this.contains(other.x, other.y) &&
                this.contains(other.x + other.w, other.y + other.h);
    }

    public boolean collides(Rect other) {
        return  this.x < (other.x + other.w) &&
                this.x + this.w > other.x    &&
                this.y  < other.y + other.h  &&
                this.y + this.h > other.y;
    }

    public float left() {
        return x;
    }

    public float top() {
        return y;
    }

    public float right() {
        return x + w;
    }

    public float bottom() {
        return y + h;
    }

    public Vector2 getCenter() {
        return new Vector2( x + (w / 2), y + (h / 2));
    }

    public Vector2 getDiagonalPoint() {
        return new Vector2(right(), bottom());
    }

    public void scale(float scale) {
        this.w *= scale;
        this.h *= scale;
    }

    public Rect copy() {
        return new Rect(x, y, w, h);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rect)) {
            return false;
        }

        Rect other = (Rect) obj;
        return Compare.equals(this.x, other.x) && Compare.equals(this.y, other.y) && Compare.equals(this.w, other.w) && Compare.equals(this.h, other.h);
    }

    @Override
    public String toString() {
        return "Rect[" + x + ", " + y + ", " + w + ", " + h + "]";
    }

    @Override
    public float area() {
        return w * h;
    }

    @Override
    public float perimeter() {
        return 2*w + 2*h;
    }

    public void setCenter(float cx, float cy) {
        this.x = cx - w  / 2.0f;
        this.y = cy - h  / 2.0f;
    }
}
