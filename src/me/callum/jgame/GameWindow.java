package me.callum.jgame;

import me.callum.jengine.math.Vector2f;
import me.callum.jengine.physics.BoxCollider;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {

    public static final String GAME_TITLE = "JGame Window";
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    private JFrame mFrame;
    private JPanel mRenderPanel;

    private Graphics2D g;

    public GameWindow() {
        setSize(WIDTH, HEIGHT);
        setBackground(Color.DARK_GRAY);

        mFrame = new JFrame(GAME_TITLE);
        mFrame.setSize(WIDTH, HEIGHT);
        mFrame.setLocationRelativeTo(null);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.add(this);
        mFrame.setVisible(true);

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTestCubeScene((Graphics2D) g);
        repaint();
    }

    float i1 = (float)Math.toRadians(45);
    float i2 = (float)Math.toRadians(45);
    float offX = 0;
    private void drawTestCubeScene(Graphics2D g) {
//        i1 += 0.001;
//        i2 += 0.002;

        var collider1 = new BoxCollider(
                new Vector2f(500+offX, 500),
                new Vector2f(100, 100),
                i1);

        var collider2 = new BoxCollider(
                new Vector2f(1000, 500),
                new Vector2f(200, 100),
                i2
        );

        g.setColor(Color.LIGHT_GRAY);

        g.setStroke(new BasicStroke(3));
        this.g = g;

        var isColliding = drawSAT(collider1, collider2);

        drawColliderPoints(collider1, isColliding);
        drawColliderPoints(collider2, isColliding);

        if(!isColliding) {

            offX += 0.2f;
        }
    }

    private Vector2f getMax(Vector2f[] vecs) {
        Vector2f max = vecs[0];

        for(int i = 1; i < vecs.length; i++){
            if(vecs[i].x>max.x) {
                max=vecs[i];
            }
        }

        return max;
    }

    private Vector2f getMin(Vector2f[] vecs) {
        Vector2f min = vecs[0];

        for(int i = 1; i < vecs.length; i++){
            if(vecs[i].x<min.x) {
                min=vecs[i];
            }
        }

        return min;
    }
    private boolean drawSAT(BoxCollider a, BoxCollider b) {
        var rotatedA = a.getRotatedExtents();
        var rotatedB = b.getRotatedExtents();

        var aCenter = a.copyCenter();
        var bCenter = b.copyCenter();

        var aTR = getMax(rotatedA);
        var bTL = getMin(rotatedB);

        var axis = new Vector2f(1, -1);

        var C = bCenter.copy().subtract(aCenter);
        var A = aTR.copy();
        var B = bTL.copy();

        var projC = Vector2f.dot(C, axis);
        var projA = Vector2f.dot(A, axis);
        var projB = Vector2f.dot(B, axis);
        var gap = projC - projA + projB;

        System.out.println(String.format("%s, %s, %s, %s", gap, projC, projA, projB));

        if (gap < 0) {
            return true;
        }
        return false;
    }

    private void drawColliderPoints(BoxCollider col, boolean isColliding) {

        g.setColor(isColliding ? Color.red : Color.green);

        var extents = col.getRotatedExtents();
        var center = col.copyCenter();

        drawLine(center.copy().add(extents[0]), center.copy().add(extents[1]));
        drawLine(center.copy().add(extents[1]), center.copy().add(extents[2]));
        drawLine(center.copy().add(extents[2]), center.copy().add(extents[3]));
        drawLine(center.copy().add(extents[3]), center.copy().add(extents[0]));

        for (var extent : extents) {
            drawVector2(center.copy().add(extent));
        }
    }

    private void drawVector2(Vector2f vec) {
        drawString(String.format("%d, %d", (int) vec.x, (int) vec.y), vec);
    }

    private void drawString(String s, Vector2f vec) {
        g.drawString(s, (int) vec.x, (int) vec.y);
    }

    private void drawLine(Vector2f start, Vector2f finish) {
        g.drawLine((int) start.x, (int) start.y, (int) finish.x, (int) finish.y);
    }

    public static void main(String[] args) {
        new GameWindow();
    }


    private void drawRotatedCollider(BoxCollider col) {
        g.translate(0, -5);
        g.setColor(Color.green);

        g.rotate(col.getRotation(),
                col.copyCenter().x,
                col.copyCenter().y);

        g.drawRect((int) (col.copyCenter().x - col.copyExtents().x),
                (int) (col.copyCenter().y - col.copyExtents().y),
                (int) (col.copyExtents().x * 2),
                (int) (col.copyExtents().y * 2));

        g.rotate(-col.getRotation(),
                col.copyCenter().x,
                col.copyCenter().y);
        g.translate(0, 5);
    }

}
