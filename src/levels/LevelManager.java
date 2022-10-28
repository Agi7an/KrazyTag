package levels;

import main.Game;
import utils.LoadSave;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSprite(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[242];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 22; j++) {
                int index = i * 22 + j;
                levelSprite[index] = img.getSubimage(j * 16, i * 16, 16, 16);
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < Game.TILES_IN_WIDTH; j++) {
                int index = levelOne.getSpriteIndex(j, i);
                g.drawImage(levelSprite[index], j * Game.TILES_SIZE, i * Game.TILES_SIZE, Game.TILES_SIZE,
                        Game.TILES_SIZE, null);
            }
        }
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return levelOne;
    }
}
