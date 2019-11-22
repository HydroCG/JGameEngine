package me.callum.jengine.entities;

import me.callum.jengine.math.Vector2;
import me.callum.jengine.math.Vector2f;

public class Entity {

    private Vector2f mPosition = new Vector2f();
    private Vector2 mSize = new Vector2();
    private float mRotation = 0;

    public Entity() {

    }

    public Vector2 getSize() {
        return mSize;
    }

    public void setSize(Vector2 size) {
        mSize = size;
    }

    public float getRotation() {
        return mRotation;
    }

    public void setRotation(float rotation) {
        mRotation = rotation;
    }

    public Vector2f getPosition() {
        return mPosition;
    }

    public void setPosition(Vector2f position) {
        mPosition = position;
    }
}
