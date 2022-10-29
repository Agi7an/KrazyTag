package ui;

import gamestates.Gamestate;
import main.Game;
import utils.LoadSave;
import static utils.Constants.UI.Buttons.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MenuButton {
    private int xPos, yPos;
    private int rowIndex;
    private int xOffsetCentre = B_WIDTH / 2;
    private int index;
    private Gamestate state;
    private BufferedImage[] images;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.state = state;
        this.rowIndex = rowIndex;
        loadImages();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCentre, yPos, B_WIDTH, B_HEIGHT);
    }

    private void loadImages() {
        images = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSprite(LoadSave.MENU_BUTTONS_ATLAS);
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT,
                    B_HEIGHT_DEFAULT);
        }
    }

    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void draw(Graphics g) {
        g.drawImage(images[index], xPos - xOffsetCentre, yPos, B_WIDTH, B_HEIGHT, null);
    }

    public void applyGameState() {
        Gamestate.STATE = state;
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
