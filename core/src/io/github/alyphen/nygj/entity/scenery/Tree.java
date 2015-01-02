package io.github.alyphen.nygj.entity.scenery;

import io.github.alyphen.nygj.NYGJ;
import io.github.alyphen.nygj.entity.Entity;
import io.github.alyphen.nygj.screen.GameScreen;

public class Tree extends Entity {
    public Tree(NYGJ game, GameScreen screen, int x, int y) {
        super(screen, game.getAnimation("tree"), x, y, 64, 64);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
