package io.github.alyphen.nygj.entity.scenery;

import io.github.alyphen.nygj.NYGJ;
import io.github.alyphen.nygj.entity.Entity;
import io.github.alyphen.nygj.screen.GameScreen;

public class Grass extends Entity {
    public Grass(NYGJ game, GameScreen screen, int x, int y) {
        super(screen, game.getAnimation("grass"), x, y, 32, 32);
    }
}
