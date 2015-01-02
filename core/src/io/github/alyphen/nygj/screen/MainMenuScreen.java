package io.github.alyphen.nygj.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import io.github.alyphen.nygj.NYGJ;

import static com.badlogic.gdx.graphics.Color.WHITE;

public class MainMenuScreen extends ScreenAdapter {

    private NYGJ game;

    private OrthographicCamera camera;

    public MainMenuScreen(NYGJ game) {
        this.game = game;
        game.getMenuMusic().setLooping(true);
        game.getMenuMusic().play();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                MainMenuScreen.this.game.setScreen(new GameScreen(MainMenuScreen.this.game));
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0F, 0.01F, 0F, 1F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        game.getSpriteBatch().begin();
        game.getSpriteBatch().setColor(WHITE);
        game.getFont().draw(game.getSpriteBatch(), "Press any key to start", 32, 32);
        game.getFont().draw(game.getSpriteBatch(), "(sorry, I got lazy on the menu, but wanted this music in anyway)", 32, 64);
        game.getSpriteBatch().end();
    }
}
