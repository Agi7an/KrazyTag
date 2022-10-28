package levels;

import main.Game;
import utils.LoadSave;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.awt.Graphics;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;

    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();
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
        g.drawImage(levelSprite[6], 0, 0, null);
    }

    public void update() {

    }
}
