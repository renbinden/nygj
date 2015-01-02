package io.github.alyphen.nygj.entity.scenery;

import io.github.alyphen.nygj.NYGJ;
import io.github.alyphen.nygj.entity.Entity;
import io.github.alyphen.nygj.screen.GameScreen;

public class Tower extends Entity {
    public Tower(NYGJ game, GameScreen screen, int x, int y) {
        super(screen, game.getAnimation("tower"), x, y, 64, 96);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
