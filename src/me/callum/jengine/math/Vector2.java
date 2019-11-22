package me.callum.jengine.math;

public class Vector2 {

    public int x;
    public int y;

    public Vector2(){
        this(0, 0);
    }

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 other) {
        this(other.x, other.y);
    }

    public Vector2(Vector2f other) {
        this((int) other.x, (int) other.y);
    }
}
