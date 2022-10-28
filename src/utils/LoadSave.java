package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class LoadSave {

        public static final String LEVEL_ATLAS = "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Terrain/Terrain (16x16).png";

        public static BufferedImage GetSprite(String path) {
                File is = new File(path);
                BufferedImage img = null;

                try {
                        img = ImageIO.read(is);
                } catch (IOException e) {
                        e.printStackTrace();
                }

                return img;
        }

        public static int[][] GetLevelData() {
                // int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
                // for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
                // for (int j = 0; j < Game.TILES_IN_WIDTH; j++) {
                // levelData[i][j] = 0;
                // }
                // }

                // 13 X 24
                int[][] levelData = {
                                { 196, 193, 194, 195, 193, 194, 195, 193, 194, 195, 193, 194, 195, 193, 194, 195, 193,
                                                194, 195, 193,
                                                194, 195, 193, 195 },
                                { 218, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                240 },
                                { 240, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                196 },
                                { 196, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                218 },
                                { 218, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                240 },
                                { 240, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                196 },
                                { 196, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 34, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                218 },
                                { 218, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                240 },
                                { 240, 5, 5, 5, 5, 5, 5, 5, 5, 34, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                196 },
                                { 196, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                218 },
                                { 218, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                240 },
                                { 240, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6,
                                                196 },
                                { 240, 193, 194, 195, 193, 194, 195, 193, 194, 195, 193, 194, 195, 193, 194, 195, 193,
                                                194, 195, 193,
                                                194, 195, 193, 195 }
                };

                return levelData;
        }
}
