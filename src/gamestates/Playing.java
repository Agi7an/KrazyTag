package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import entities.Player;
import entities.Enemy;
import levels.*;
import main.Game;
import ui.PauseOverlay;
import utils.LoadSave;
import java.awt.Color;
import java.awt.image.BufferedImage;
import static utils.Constants.Environment.*;
import java.util.Random;

public class Playing extends State implements Statemethods {

    private Player player;
    private Enemy enemy;
    private LevelManager levelManager;
    private boolean paused = false;
    private PauseOverlay pauseOverlay;

    private int xLevelOffset;
    private int levelTilesWide = LoadSave.GetLevelData()[0].length;
    private int maxTilesOffset = levelTilesWide - Game.TILES_IN_WIDTH;
    private int maxLevelOffsetX = maxTilesOffset * Game.TILES_SIZE;
    private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);

    private BufferedImage background, bigCloud, smallCloud;
    private int[] smallCloudsY;
    private Random rand = new Random();

    public Playing(Game game) {
        super(game);
        initClasses();

        background = LoadSave.GetSprite(LoadSave.LEVEL_BACKGROUND);
        bigCloud = LoadSave.GetSprite(LoadSave.BIG_CLOUDS);
        smallCloud = LoadSave.GetSprite(LoadSave.SMALL_CLOUDS);
        smallCloudsY = new int[8];
        for (int i = 0; i < smallCloudsY.length; i++) {
            smallCloudsY[i] = 50 + rand.nextInt(170);
        }
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(100, 100, (int) (16 * Game.SCALE), (int) (16 * Game.SCALE));
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
        enemy = new Enemy(200, 100, (int) (16 * Game.SCALE), (int) (16 * Game.SCALE));
        enemy.loadLevelData(levelManager.getCurrentLevel().getLevelData());
        pauseOverlay = new PauseOverlay(this);
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    @Override
    public void update() {
        if (!paused) {
            levelManager.update();
            player.update();
            enemy.update();
            if (enemy.hasCaughtPlayer(player.getHitBox())) {
                Gamestate.STATE = Gamestate.QUIT;
            }
            checkCloseToBorder();
        } else {
            pauseOverlay.update();
        }
    }

    private void checkCloseToBorder() {
        int playerX = (int) player.getHitBox().x;
        int diff = playerX - xLevelOffset;

        if (diff > rightBorder) {
            xLevelOffset += diff - rightBorder;
        } else if (diff < leftBorder) {
            xLevelOffset += diff - leftBorder;
        }

        if (xLevelOffset > maxLevelOffsetX) {
            xLevelOffset = maxLevelOffsetX;
        } else if (xLevelOffset < 0) {
            xLevelOffset = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);

        drawClouds(g);

        levelManager.draw(g, xLevelOffset);

        enemy.render(g, xLevelOffset);
        player.render(g, xLevelOffset);

        if (paused) {
            g.setColor(new Color(0, 0, 0, 200));
            g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
            pauseOverlay.draw(g);
        }
    }

    private void drawClouds(Graphics g) {
        for (int i = 0; i < 3; i++) {
            g.drawImage(bigCloud, i * BIG_CLOUD_WIDTH - (int) (xLevelOffset * 0.3), 273, BIG_CLOUD_WIDTH,
                    BIG_CLOUD_HEIGHT, null);
        }

        for (int i = 0; i < smallCloudsY.length; i++) {
            g.drawImage(smallCloud, i * 4 * SMALL_CLOUD_WIDTH - (int) (xLevelOffset * 0.7), smallCloudsY[i],
                    SMALL_CLOUD_WIDTH, SMALL_CLOUD_HEIGHT,
                    null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            enemy.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused) {
            pauseOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseMoved(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_RIGHT:
                enemy.setRight(true);
                break;
            case KeyEvent.VK_LEFT:
                enemy.setLeft(true);
                break;
            case KeyEvent.VK_UP:
                enemy.setJump(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
                Gamestate.STATE = Gamestate.MENU;
                break;
            case KeyEvent.VK_ESCAPE:
                paused = !paused;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
            case KeyEvent.VK_RIGHT:
                enemy.setRight(false);
                break;
            case KeyEvent.VK_LEFT:
                enemy.setLeft(false);
                break;
            case KeyEvent.VK_UP:
                enemy.setJump(false);
                break;
        }
    }

    public void unpauseGame() {
        paused = false;
    }
}
