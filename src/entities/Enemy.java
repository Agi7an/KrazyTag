package entities;

import java.awt.image.BufferedImage;
import main.Game;
import java.awt.Graphics;
import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;
import static utils.LoadSave.*;
import java.awt.geom.*;

public class Enemy extends Entity {
    private BufferedImage[][] animations;
    private BufferedImage img;
    private int aniTick = 0, aniIndex = 0, aniSpeed = 7;
    private int enemyAction = IDLE;
    private boolean left, up, right, down;
    private boolean moving = false, attacking = false;
    private float enemySpeed = 2.0f;
    private int[][] levelData;
    // Hitbox Offset
    // private float xDrawOffset = 6 * Game.SCALE, yDrawOffset = 5 * Game.SCALE;
    private float xDrawOffset = 3 * Game.SCALE, yDrawOffset = 3 * Game.SCALE;

    // Jumping and Gravity
    private boolean jump;
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        // Hitbox Width and Height
        // initHitBox(x, y, 20 * Game.SCALE, 26 * Game.SCALE);
        initHitBox(x, y, (int) (10 * Game.SCALE), (int) (12 * Game.SCALE));
    }

    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g, int levelOffset) {
        g.drawImage(animations[enemyAction][aniIndex], (int) (hitBox.x - xDrawOffset) - levelOffset,
                (int) (hitBox.y - yDrawOffset),
                (int) (width),
                (int) (height),
                null);
        // drawHitBox(g, levelOffset);
    }

    private void loadAnimations() {
        animations = new BufferedImage[7][12];

        img = GetSprite(
                "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Mask Dude/Idle (32x32).png");
        for (int i = 0; i < 11; i++) {
            animations[0][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        img = GetSprite(
                "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Mask Dude/Run (32x32).png");
        for (int i = 0; i < 12; i++) {
            animations[1][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        img = GetSprite(
                "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Mask Dude/Hit (32x32).png");
        for (int i = 0; i < 7; i++) {
            animations[2][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        img = GetSprite(
                "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Mask Dude/Double Jump (32x32).png");
        for (int i = 0; i < 6; i++) {
            animations[3][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        img = GetSprite(
                "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Mask Dude/Wall Jump (32x32).png");
        for (int i = 0; i < 5; i++) {
            animations[4][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        img = GetSprite(
                "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Mask Dude/Jump (32x32).png");
        for (int i = 0; i < 1; i++) {
            animations[5][i] = img.getSubimage(i * 32, 0, 32, 32);
        }

        img = GetSprite(
                "D:/TCS/fifthSem/JAVA Lab/Package/Game/res/Main Characters/Mask Dude/Fall (32x32).png");
        for (int i = 0; i < 1; i++) {
            animations[6][i] = img.getSubimage(i * 32, 0, 32, 32);
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(enemyAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {
        int startAni = enemyAction;

        if (moving) {
            enemyAction = RUN;
        } else {
            enemyAction = IDLE;
        }

        if (inAir) {
            if (airSpeed < 0) {
                enemyAction = JUMP;
            } else {
                enemyAction = FALL;
            }
        }

        if (attacking) {
            enemyAction = HIT;
        }

        if (startAni != enemyAction) {
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

        if (!inAir) {
            if ((!left && !right) || (left && right)) {
                return;
            }
        }

        float xSpeed = 0;

        if (left) {
            xSpeed -= enemySpeed;
        }
        if (right) {
            xSpeed += enemySpeed;
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

    public boolean hasCaughtPlayer(Rectangle2D.Float playerHitbox) {
        if (hitBox.x < playerHitbox.x + playerHitbox.width && hitBox.x > playerHitbox.x && hitBox.y > playerHitbox.y
                && hitBox.y <= playerHitbox.y + playerHitbox.height) {
            return true;
        } else if (hitBox.x < playerHitbox.x + playerHitbox.width && hitBox.x > playerHitbox.x
                && hitBox.y + hitBox.height > playerHitbox.y
                && hitBox.y + hitBox.height <= playerHitbox.y + playerHitbox.height) {
            return true;
        } else if (hitBox.x + hitBox.width < playerHitbox.x + playerHitbox.width
                && hitBox.x + hitBox.width > playerHitbox.x
                && hitBox.y > playerHitbox.y
                && hitBox.y <= playerHitbox.y + playerHitbox.height) {
            return true;
        } else if (hitBox.x + hitBox.width < playerHitbox.x + playerHitbox.width
                && hitBox.x + hitBox.width > playerHitbox.x
                && hitBox.y + hitBox.height > playerHitbox.y
                && hitBox.y + hitBox.height <= playerHitbox.y + playerHitbox.height) {
            return true;
        } else {
            return false;
        }
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}
