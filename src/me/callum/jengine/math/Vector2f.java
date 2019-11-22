package me.callum.jengine.math;

public class Vector2f {

    public float x;
    public float y;

    public Vector2f() {
        this(0, 0);
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f source) {
        this(source.x, source.y);
    }

    public Vector2f rotate(float theta) {
        var cos = Math.cos((double) theta);
        var sin = Math.sin((double) theta);

        var newX = (float) (x * cos - y * sin);
        var newY = (float) (x * sin + y * cos);

        x = newX;
        y = newY;

        return this;
    }

    public Vector2f scale(float scale) {
        x *= scale;
        y *= scale;
        return this;
    }


    public Vector2f add(Vector2f other) {
        x += other.x;
        y += other.y;
        return this;
    }

    public Vector2f subtract(Vector2f other) {
        x -= other.x;
        y -= other.y;
        return this;
    }


    public Vector2f copy() {
        return new Vector2f(this);
    }

    public static float dot(Vector2f a, Vector2f b) {
        return (a.x * b.x) + (a.y * b.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
