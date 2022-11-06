package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utils.LoadSave;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.image.BufferedImage;

import java.awt.event.MouseEvent;
import static utils.Constants.UI.URMButtons.*;

public class GameOverOverlay {
    private Playing playing;
    private BufferedImage image;
    private BufferedImage[] numbers;
    private int x, y, width, height;
    private UrmButton menu, again;
    private int score;

    public GameOverOverlay(Playing playing) {
        this.playing = playing;
        createImage();
        createButtons();
    }

    private void createButtons() {
        int menuX = 495;
        int againX = 600;
        int y = 300;
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
        again = new UrmButton(againX, y, URM_SIZE, URM_SIZE, 1);
    }

    private void createImage() {
        image = LoadSave.GetSprite(LoadSave.DEATH_SCREEN);
        width = (int) (image.getWidth() * Game.SCALE / 3);
        height = (int) (image.getHeight() * Game.SCALE / 3);
        x = Game.GAME_WIDTH / 2 - width / 2;
        y = 200;

        numbers = new BufferedImage[10];
        BufferedImage temp = LoadSave.GetSprite(LoadSave.WHITE_TEXT);
        for (int i = 0; i < 10; i++) {
            numbers[i] = temp.getSubimage(i * 8, 30, 8, 10);
        }
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        g.drawImage(image, x, y, width, height, null);

        score = playing.getPlayer().getScore();
        int length;
        if (score == 0) {
            length = 1;
            g.drawImage(numbers[score],
                    Game.GAME_WIDTH / 2 - 25, 100, 50,
                    50, null);
        } else {
            length = (int) (Math.ceil(Math.log10(score)));
            int n = length;
            while (n > 0) {
                g.drawImage(numbers[(int) (score / Math.pow(10, n - 1))],
                        Game.GAME_WIDTH / 2 - ((length / 2) * 50) + (length - n) * 50, 100, 50,
                        50, null);
                score = (int) (score % Math.pow(10, n - 1));
                n -= 1;
            }
        }

        menu.draw(g);
        again.draw(g);
    }

    public void update() {
        menu.update();
        again.update();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            playing.resetAll();
            Gamestate.STATE = Gamestate.MENU;
        }
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, menu)) {
            menu.setMousePressed(true);
        } else if (isIn(e, again)) {
            again.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e, menu)) {
            if (menu.isMousePressed()) {
                Gamestate.STATE = Gamestate.MENU;
                playing.unpauseGame();
            }
        } else if (isIn(e, again)) {
            if (again.isMousePressed()) {
                playing.resetAll();
            }
        }

        menu.resetBools();
        again.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        menu.setMouseOver(false);
        again.setMouseOver(false);

        if (isIn(e, menu)) {
            menu.setMouseOver(true);
        } else if (isIn(e, again)) {
            again.setMouseOver(true);
        }
    }

    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}
