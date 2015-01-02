package io.github.alyphen.nygj.entity.hero;

import io.github.alyphen.nygj.NYGJ;
import io.github.alyphen.nygj.entity.Beam;
import io.github.alyphen.nygj.entity.Entity;
import io.github.alyphen.nygj.screen.GameScreen;

public abstract class Hero extends Entity {

    private NYGJ game;
    private GameScreen screen;
    private String name;

    public Hero(NYGJ game, GameScreen screen, String name, int x, int y) {
        super(screen, game.getHeroAnimation(name, "down", false), x, y, 32, 32);
        this.game = game;
        this.screen = screen;
        this.name = name;
    }

    @Override
    public void setDy(int dy) {
        super.setDy(dy);
        if (dy > 0)
            setAnimation(game.getHeroAnimation(name, "down", false));
        else if (dy < 0)
            setAnimation(game.getHeroAnimation(name, "up", false));
    }

    @Override
    public void setDx(int dx) {
        super.setDx(dx);
        if (dx > 0)
            setAnimation(game.getHeroAnimation(name, "right", false));
        else if (dx < 0)
            setAnimation(game.getHeroAnimation(name, "left", false));
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    public void shoot() {
        String direction = "down";
        if (getAnimation() == game.getHeroAnimation(name, "up", false)) {
            direction = "up";
        } else if (getAnimation() == game.getHeroAnimation(name, "down", false)) {
            direction = "down";
        } else if (getAnimation() == game.getHeroAnimation(name, "left", false)) {
            direction = "left";
        } else if (getAnimation() == game.getHeroAnimation(name, "right", false)) {
            direction = "right";
        }
        screen.addBeam(new Beam(game, screen, getX(), getY(), direction));
    }
}
