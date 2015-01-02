package io.github.alyphen.nygj.entity.scenery;

import com.badlogic.gdx.graphics.g2d.Animation;
import io.github.alyphen.nygj.NYGJ;
import io.github.alyphen.nygj.entity.Entity;
import io.github.alyphen.nygj.screen.GameScreen;

public class LilyPad extends Entity {
    public LilyPad(NYGJ game, GameScreen screen, int x, int y) {
        super(screen, game.getAnimation("lilypad"), x, y, 32, 32);
    }
}
