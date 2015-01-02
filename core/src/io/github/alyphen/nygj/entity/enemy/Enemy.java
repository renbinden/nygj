package io.github.alyphen.nygj.entity.enemy;

import io.github.alyphen.nygj.NYGJ;
import io.github.alyphen.nygj.entity.Entity;
import io.github.alyphen.nygj.screen.GameScreen;

public class Enemy extends Entity {

    private GameScreen screen;
    public Enemy(NYGJ game, GameScreen screen, int x, int y) {
        super(screen, game.getAnimation("enemy"), x, y, 32, 32);
        this.screen = screen;
    }

    @Override
    public void onTick() {
        if (screen.getHero().getX() < getX()) {
            setDx(-2);
            setMovingHorizontally(true);
        } else if (screen.getHero().getX() > getX()) {
            setDx(2);
            setMovingHorizontally(true);
        } else {
            setMovingHorizontally(false);
        }

        if (screen.getHero().getY() < getY()) {
            setDy(-2);
            setMovingVertically(true);
        } else if (screen.getHero().getY() > getY()) {
            setDy(2);
            setMovingVertically(true);
        } else {
            setMovingVertically(false);
        }
        super.onTick();
    }
}
