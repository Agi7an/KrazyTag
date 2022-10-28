package entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;
import java.awt.Graphics;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private BufferedImage img;
    private int aniTick = 0, aniIndex = 0, aniSpeed = 7;
    private int playerAction = IDLE;
    private boolean left, up, right, down;
    private boolean moving = false, attacking = false;
    private float playerSpeed = 2.0f;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
        // Dummy
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 64, 64, null);
    }

    private void loadAnimations() {
        animations = new BufferedImage[7][12];

        importImage("D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Ninja Frog/Idle (32x32).png");
        for (int i = 0; i < 11; i++) {
            animations[0][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        importImage("D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Ninja Frog/Run (32x32).png");
        for (int i = 0; i < 12; i++) {
            animations[1][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        importImage("D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Ninja Frog/Hit (32x32).png");
        for (int i = 0; i < 7; i++) {
            animations[2][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        importImage("D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Ninja Frog/Double Jump (32x32).png");
        for (int i = 0; i < 6; i++) {
            animations[3][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        importImage("D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Ninja Frog/Wall Jump (32x32).png");
        for (int i = 0; i < 5; i++) {
            animations[4][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        importImage("D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Ninja Frog/Jump (32x32).png");
        for (int i = 0; i < 1; i++) {
            animations[5][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        importImage("D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Ninja Frog/Fall (32x32).png");
        for (int i = 0; i < 1; i++) {
            animations[6][i] = img.getSubimage(i * 32, 0, 32, 32);
        }
    }

    private void importImage(String path) {
        File is = new File(path);

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {
        int startAni = playerAction;

        if (moving) {
            playerAction = RUN;
        } else {
            playerAction = IDLE;
        }

        if (attacking) {
            playerAction = HIT;
        }

        if (startAni != playerAction) {
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    public void updatePosition() {
        moving = false;

        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }

        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public void resetDirectionBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
