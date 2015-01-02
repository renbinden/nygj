package io.github.alyphen.nygj.entity.scenery;

import io.github.alyphen.nygj.NYGJ;
import io.github.alyphen.nygj.entity.Entity;
import io.github.alyphen.nygj.screen.GameScreen;

public class Rock extends Entity {
    public Rock(NYGJ game, GameScreen screen, int x, int y) {
        super(screen, game.getAnimation("rock"), x, y, 32, 32);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
