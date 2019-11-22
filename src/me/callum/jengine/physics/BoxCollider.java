package me.callum.jengine.physics;

import me.callum.jengine.math.Vector2f;

public class BoxCollider {

    private Vector2f mCenter;
    private Vector2f mExtents;
    private float mRotation;

    public BoxCollider(Vector2f center, Vector2f extents, float rotation) {
        mCenter = center;
        mExtents = extents;
        mRotation = rotation;
    }

    public Vector2f[] getRotatedExtents() {

        var rotatedExtents = new Vector2f[4];

        rotatedExtents[0] = new Vector2f(-mExtents.x, mExtents.y).rotate(mRotation);
        rotatedExtents[1] = new Vector2f(mExtents.x, mExtents.y).rotate(mRotation);
        rotatedExtents[2] = new Vector2f(mExtents.x, -mExtents.y).rotate(mRotation);
        rotatedExtents[3] = new Vector2f(-mExtents.x, -mExtents.y).rotate(mRotation);

        return rotatedExtents;
    }

    public Vector2f copyCenter() {
        return mCenter.copy();
    }

    public Vector2f copyExtents() {
        return mExtents.copy();
    }

    public float getRotation() {
        return mRotation;
    }
}
