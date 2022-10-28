package entities;

import java.awt.image.BufferedImage;

import main.Game;
import java.awt.Graphics;
import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;
import static utils.LoadSave.*;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private BufferedImage img;
    private int aniTick = 0, aniIndex = 0, aniSpeed = 7;
    private int playerAction = IDLE;
    private boolean left, up, right, down;
    private boolean moving = false, attacking = false;
    private float playerSpeed = 2.0f;
    private int[][] levelData;
    // Hitbox Offset
    private float xDrawOffset = 6 * Game.SCALE, yDrawOffset = 5 * Game.SCALE;

    // Jumping and Gravity
    private boolean jump;
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        // Hitbox Width and Height
        initHitBox(x, y, 20 * Game.SCALE, 26 * Game.SCALE);
    }

    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) (hitBox.x - xDrawOffset), (int) (hitBox.y - yDrawOffset),
                (int) (32 * Game.SCALE),
                (int) (32 * Game.SCALE),
                null);
        drawHitBox(g);
    }

    private void loadAnimations() {
        animations = new BufferedImage[7][12];

        img = GetSprite(
                "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Ninja Frog/Idle (32x32).png");
        for (int i = 0; i < 11; i++) {
            animations[0][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        img = GetSprite(
                "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Ninja Frog/Run (32x32).png");
        for (int i = 0; i < 12; i++) {
            animations[1][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        img = GetSprite(
                "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Ninja Frog/Hit (32x32).png");
        for (int i = 0; i < 7; i++) {
            animations[2][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        img = GetSprite(
                "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Ninja Frog/Double Jump (32x32).png");
        for (int i = 0; i < 6; i++) {
            animations[3][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        img = GetSprite(
                "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Ninja Frog/Wall Jump (32x32).png");
        for (int i = 0; i < 5; i++) {
            animations[4][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        img = GetSprite(
                "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Ninja Frog/Jump (32x32).png");
        for (int i = 0; i < 1; i++) {
            animations[5][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        img = GetSprite(
                "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Ninja Frog/Fall (32x32).png");
        for (int i = 0; i < 1; i++) {
            animations[6][i] = img.getSubimage(i * 32, 0, 32, 32);
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

        if (inAir) {
            if (airSpeed < 0) {
                playerAction = JUMP;
            } else {
                playerAction = FALL;
            }
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

        if (jump) {
            jump();
        }
        if (!left && !right && !inAir) {
            return;
        }

        float xSpeed = 0;

        if (left) {
            xSpeed -= playerSpeed;
        }
        if (right) {
            xSpeed += playerSpeed;
        }
        if (!inAir) {
            if (!IsEntityOnFloor(hitBox, levelData)) {
                inAir = true;
            }
        }

        if (inAir) {
            if (CanMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, levelData)) {
                hitBox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitBox.y = GetEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
        }
        moving = true;
    }

    private void jump() {
        if (inAir) {
            return;
        } else {
            inAir = true;
            airSpeed = jumpSpeed;
        }
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width,
                hitBox.height, levelData)) {
            hitBox.x += xSpeed;
        } else {
            hitBox.x = GetEntityXPosNextToWall(hitBox, xSpeed);
        }
    }

    public void loadLevelData(int[][] levelData) {
        this.levelData = levelData;
        if (!IsEntityOnFloor(hitBox, levelData)) {
            inAir = true;
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

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}
