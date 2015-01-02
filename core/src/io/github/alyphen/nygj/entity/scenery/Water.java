package io.github.alyphen.nygj.entity.scenery;

import io.github.alyphen.nygj.NYGJ;
import io.github.alyphen.nygj.entity.Entity;
import io.github.alyphen.nygj.screen.GameScreen;

public class Water extends Entity {
    public Water(NYGJ game, GameScreen screen, int x, int y) {
        super(screen, game.getAnimation("water"), x, y, 32, 32);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
