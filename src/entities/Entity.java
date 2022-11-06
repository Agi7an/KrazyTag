package entities;

import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.Color;

public abstract class Entity {
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitBox;
    protected int state;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void initHitBox(float x, float y, int width, int height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }

    protected void drawHitBox(Graphics g, int levelOffset) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitBox.x - levelOffset, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
