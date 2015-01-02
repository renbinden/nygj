package io.github.alyphen.nygj.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import io.github.alyphen.nygj.screen.GameScreen;

public abstract class Entity {

    private GameScreen screen;

    private Animation animation;
    private int x, y, width, height, dx, dy;
    private float stateTime;
    private float moveTime;
    private boolean movingHorizontally, movingVertically;

    public Entity(GameScreen screen, Animation animation, int x, int y, int width, int height) {
        this.screen = screen;
        this.animation = animation;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMovingHorizontally(boolean movingHorizontally) {
        this.movingHorizontally = movingHorizontally;
    }

    public boolean isMovingHorizontally() {
        return movingHorizontally;
    }

    public void setMovingVertically(boolean movingVertically) {
        this.movingVertically = movingVertically;
    }

    public boolean isMovingVertically() {
        return movingVertically;
    }

    public Rectangle getRelativeBounds(int dx, int dy) {
        return new Rectangle(x + dx, y + dy, width, height);
    }

    public Rectangle getBounds() {
        return getRelativeBounds(0, 0);
    }

    public void onTick() {
        stateTime += Gdx.graphics.getDeltaTime();
        if (!isMovingHorizontally() && getX() % 32 == 0) setDx(0);
        if (!isMovingVertically() && getY() % 32 == 0) setDy(0);
        if (stateTime - moveTime >= 0.05) {
            moveTime += 0.05;
            if (getDx() != 0 || getDy() != 0) {
                boolean freeX = true, freeY = true;
                for (Entity other : new Array<>(screen.getEntities())) {
                    if (other.isSolid() && isSolid() && other != this && getRelativeBounds(getDx(), 0).overlaps(other.getBounds()))
                        freeX = false;
                    if (other.isSolid() && getRelativeBounds(0, getDy()).overlaps(other.getBounds()))
                        freeY = false;
                }
                if (freeX) {
                    setX(getX() + getDx());
                }
                if (freeY) {
                    setY(getY() + getDy());
                }
            }
        }
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(getAnimation().getKeyFrame(stateTime), getX(), getY());
    }

    public boolean isSolid() {
        return false;
    }

}
