package io.github.alyphen.nygj.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import io.github.alyphen.nygj.NYGJ;
import io.github.alyphen.nygj.entity.enemy.Enemy;
import io.github.alyphen.nygj.screen.GameScreen;

public class Beam {

    private GameScreen screen;
    private TextureRegion textureRegion;
    private int x;
    private int y;
    private String direction;

    public Beam(NYGJ game, GameScreen screen, int x, int y, String direction) {
        this.screen = screen;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.textureRegion = game.getBeam();
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void onTick() {
        switch (getDirection()) {
            case "up":
                setY(getY() - 32);
                break;
            case "down":
                setY(getY() + 32);
                break;
            case "right":
                setX(getX() + 32);
                break;
            case "left":
                setX(getX() - 32);
                break;
        }
        for (Entity entity : new Array<>(screen.getEntities())) {
            if (entity instanceof Enemy && entity.getBounds().overlaps(new Rectangle(getX(), getY(), 32, 32))) screen.removeLater(entity);
        }
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(textureRegion, x, y);
    }

}
