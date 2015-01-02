package io.github.alyphen.nygj.controller;

import com.badlogic.gdx.InputAdapter;
import io.github.alyphen.nygj.entity.hero.Hero;

import static com.badlogic.gdx.Input.Keys.*;

public class KeyboardController extends InputAdapter {

    private Hero hero;

    public KeyboardController(Hero hero) {
        this.hero = hero;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case W:case UP:
                hero.setDy(-4);
                hero.setMovingVertically(true);
                break;
            case A:case LEFT:
                hero.setDx(-4);
                hero.setMovingHorizontally(true);
                break;
            case S:case DOWN:
                hero.setDy(4);
                hero.setMovingVertically(true);
                break;
            case D:case RIGHT:
                hero.setDx(4);
                hero.setMovingHorizontally(true);
                break;
            case SPACE:
                hero.shoot();
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case W:case UP:
            case S:case DOWN:
                hero.setMovingVertically(false);
                break;
            case A:case LEFT:
            case D:case RIGHT:
                hero.setMovingHorizontally(false);
                break;
        }
        return true;
    }

}
