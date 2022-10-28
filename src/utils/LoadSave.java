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
}
