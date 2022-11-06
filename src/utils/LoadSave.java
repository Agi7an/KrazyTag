package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class LoadSave {

        public static final String LEVEL_ATLAS = "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Terrain/Terrain (16x16).png";
        public static final String MENU_BUTTONS_ATLAS = "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Extras/button_atlas.png";
        public static final String MENU_BACKGROUND = "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Extras/menu_background.png";
        public static final String PAUSE_BACKGROUND = "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Extras/pause_menu.png";
        public static final String SOUND_BUTTONS = "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Extras/sound_button.png";
        public static final String URM_BUTTONS = "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Extras/urm_buttons.png";
        public static final String MENU_BACKGROUND_IMAGE = "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Extras/background_menu.png";
        public static final String LEVEL_BACKGROUND = "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Extras/playing_bg_img.png";
        public static final String BIG_CLOUDS = "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Extras/big_clouds.png";
        public static final String SMALL_CLOUDS = "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Extras/small_clouds.png";
        public static final String DEATH_SCREEN = "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Extras/death_screen.png";

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
                // 13 X 48
                int[][] levelData = {
                                { 196, 193, 194, 195, 193, 194, 195, 193, 194, 195, 193, 194, 195, 193, 194, 195, 193,
                                                194, 195, 193, 194, 195, 193, 195, 196, 193, 194, 195, 193, 194, 195,
                                                193, 194, 195, 193, 194, 195, 193, 194, 195, 193, 194, 195, 193, 194,
                                                195, 193, 195 },
                                { 218, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                240 },
                                { 240, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                196 },
                                { 196, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                218 },
                                { 218, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                240 },
                                { 240, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                196 },
                                { 196, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 34, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 34, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                218 },
                                { 218, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                240 },
                                { 240, 5, 5, 5, 5, 5, 5, 5, 5, 34, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 34, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                196 },
                                { 196, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                218 },
                                { 218, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                                                240 },
                                { 240, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6,
                                                7, 6, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6,
                                                196 },
                                { 240, 193, 194, 195, 193, 194, 195, 193, 194, 195, 193, 194, 195, 193, 194, 195, 193,
                                                194, 195, 193,
                                                194, 195, 193, 195, 193, 193, 194, 195, 193, 194, 195, 193, 194, 195,
                                                193, 194, 195, 193, 194, 195, 193,
                                                194, 195, 193,
                                                194, 195, 193, 195 }
                };

                return levelData;
        }
}
