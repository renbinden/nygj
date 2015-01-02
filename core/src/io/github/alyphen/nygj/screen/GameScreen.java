package io.github.alyphen.nygj.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import io.github.alyphen.nygj.NYGJ;
import io.github.alyphen.nygj.controller.KeyboardController;
import io.github.alyphen.nygj.entity.Beam;
import io.github.alyphen.nygj.entity.Entity;
import io.github.alyphen.nygj.entity.enemy.Enemy;
import io.github.alyphen.nygj.entity.hero.Hero;
import io.github.alyphen.nygj.entity.hero.Wizard;
import io.github.alyphen.nygj.entity.scenery.*;

import java.util.Random;

public class GameScreen extends ScreenAdapter {

    private NYGJ game;
    private Array<Entity> entities;
    private Array<Beam> beams;
    private OrthographicCamera camera;
    private Hero hero;
    private float timeTillEnemySpawn;
    private Array<Entity> remove;

    public GameScreen(NYGJ game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 800, 600);
        game.getMenuMusic().stop();
        game.getIngameMusic().setLooping(true);
        game.getIngameMusic().play();
        entities = new Array<>();
        remove = new Array<>();
        beams = new Array<>();
        timeTillEnemySpawn = 1F;
        String map =
                "t t t t t t t t t t t t t t t t \n" +
                "                                \n" +
                "t ggggggggggggggggggggggggggggt \n" +
                "  ggggggggggggggggggggggggrggg  \n" +
                "t ggggggggggggggT ggggggggggggt \n" +
                "  ggrggggggrgggg  gggggggggggg  \n" +
                "t gggggggggggggg  ggrgggggggggt \n" +
                "  gggggggggrggggggggggggggrggg  \n" +
                "t ggggrggggggggggggggggt gggggt \n" +
                "  gggggggggggggggt gggg  ggggg  \n" +
                "t ggggggggggwwwwg  gggggggggggt \n" +
                "  gggggwwwwwwwwgggggggwwwwgggg  \n" +
                "t ggwwwwwwwwwwwwwlwwwwwwwwwwggt \n" +
                "  wwwwwwwwwwllllllwwwwlllllwww  \n" +
                "t wwwwwwwwwwlwwwwwwwwwlwwwwwwwt \n" +
                "  wwwwwwwwwwlwwwwwwwwwlwwwwwww  \n" +
                "t wwwwwwwwwwlwwwwwwwwwlwwwwwwwt \n" +
                "  wwwwwwwwwwlwwwwwwwwwlwwwwwww  \n" +
                "t wwwwwwwwwwlwwwwwwwwwlwwwwwwwt \n" +
                "  wwwwwwwwwwlwwwwwwwwwlwwwwwww  \n" +
                "t wwwwwwwwwwlwwwwwwwwwlwwwwwwwt \n" +
                "  wwwwwwwwwwlwwwwwwwwwlwwwwwww  \n" +
                "t wwwwwwwwwwlwwwwwwwwwlwwwwwwwt \n" +
                "  wwwwwwwwwwlwwwlllllllwwwwwww  \n" +
                "t wwwwwwwwwwlwwwlwwwwwwwwwwwwwt \n" +
                "  wwwwwwwwwwlwwwlwwwwwwwwwwwww  \n" +
                "t wwwwwwwwwwlwwwlwwwwwwwwwwwwwt \n" +
                "  wwwwwwwwwwlwwwlwwwwwwlwwwwww  \n" +
                "t wwwwwwwwwwlwwwlwwwwwwlwwwwwwt \n" +
                "  wwwwwwwwwwlwwwlwwwwwwlwwwwww  \n" +
                "t wwwwwwwwwwlwwwlwwwwwwlwwwwwwt \n" +
                "  wwwwwwwwwwlwwwlwwwwwwlwwwwww  \n" +
                "t wwwwwwwwwwlwwwlwwwwwwlwwwwwwt \n" +
                "  wwwwwwwwwwlwwwlwwwwwwlwwwwww  \n" +
                "t wwwwwwwwwwlllllllllllllllwwwt \n" +
                "  wwwwwwwwwwwwwwwwwwlwwwwwwwww  \n" +
                "t wwwwwwwwwwwwwwwwwwlwwwwwwwwwt \n" +
                "  wwwwwwwwwwwwwwwwlllwwwwwwwww  \n" +
                "t wwwwwwwwwwwwwwwwlwwwwwwwwwwwt \n" +
                "  wwwwwwwwwwwwwwwwlwwwwwwwwwww  \n" +
                "t ggggggggwwwwgggwlwwwwggggwwwt \n" +
                "  ggggggggggwwwwgggggwwwwgwwwg  \n" +
                "t ggggggggggggggggggggggggggggt \n" +
                "  gggggggggggggggggggggggggggg  \n" +
                "t ggggggggggggggggggggggggggggt \n" +
                "  ggggggggggggggggggggggrggggg  \n" +
                "t ggggggggggggggggggggggggggggt \n" +
                "  ggggggggrggggggggggggggggggg  \n" +
                "t ggggggggggggggggggggggggggggt \n" +
                "  ggggggggggggggggggrggggggggg  \n" +
                "t ggggggggggggggggggggggggggggt \n" +
                "  gggggggggggggggggggggggggggg  \n" +
                "t ggggggggggggggggggggggggggggt \n" +
                "  gggggggggggggggggggggggggggg  \n" +
                "t gggggggggggggggggggggrggggggt \n" +
                "  gggggggt t t t t t t t ggggg  \n" +
                "t ggggggg                gggggt \n" +
                "  gggggggggggggggggggggggggggg  \n" +
                "t ggggggggggggggggggggggggggggt \n" +
                "  gggggggggggggggggggggggggggg  \n" +
                "t ggggt ggggggggggggggggggggggt \n" +
                "  gggg  gggggggggggggggggggggg  \n" +
                "t ggggggggggggggggggggggggggggt \n" +
                "  ggggggggggggggggggggt gggggg  \n" +
                "t gggggggggggggggggggg  ggggggt \n" +
                "  gggggggggggggggggggggggggggg  \n" +
                "t gggggggt gggggggggggggggggggt \n" +
                "  ggggggg  ggggggggggggggggggg  \n" +
                "t gggggggggggggghgggggggggggggt \n" +
                "  gggggggggggggggggggggggggggg  \n" +
                "t ggggggggggggggggggggggggggggt \n" +
                "  gggggggggggggggggggggggggggg  \n" +
                "t t t t t t t t t t t t t t t t \n" +
                "                                ";
        int x = 0, y = 0;
        for (char c : map.toCharArray()) {
            switch (c) {
                case 't':
                    entities.add(new Tree(game, this, x, y));
                    break;
                case 'g':
                    entities.add(new Grass(game, this, x, y));
                    break;
                case 'T':
                    entities.add(new Tower(game, this, x, y));
                    break;
                case 'w':
                    entities.add(new Water(game, this, x, y));
                    break;
                case 'l':
                    entities.add(new LilyPad(game, this, x, y));
                    break;
                case 'r':
                    entities.add(new Rock(game, this, x, y));
                    break;
                case 'h':
                    entities.add(new Grass(game, this, x, y));
                    hero = new Wizard(game, this, x, y);
                    break;
            }
            if (c == '\n') {
                x = 0;
                y += 32;
            } else {
                x += 32;
            }
        }
        Gdx.input.setInputProcessor(new KeyboardController(hero));
    }

    public Array<Entity> getEntities() {
        return entities;
    }

    @Override
    public void render(float delta) {
        entities.removeAll(remove, true);
        timeTillEnemySpawn -= Gdx.graphics.getDeltaTime();
        if (timeTillEnemySpawn <= 0F) {
            getEntities().add(new Enemy(game, this, hero.getX(), hero.getY() - 480));
            timeTillEnemySpawn = 1F;
        }
        for (Entity entity : getEntities()) {
            entity.onTick();
        }
        for (Beam beam : beams) {
            beam.onTick();
        }
        hero.onTick();
        camera.position.set(hero.getX(), hero.getY(), 0);
        camera.update();
        Gdx.gl.glClearColor(0F, 0F, 0F, 1F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        game.getSpriteBatch().begin();
        for (Entity entity : getEntities()) {
            entity.render(game.getSpriteBatch());
        }
        for (Beam beam : beams) {
            beam.render(game.getSpriteBatch());
        }
        hero.render(game.getSpriteBatch());
        game.getSpriteBatch().end();
    }

    public Hero getHero() {
        return hero;
    }

    public void addBeam(Beam beam) {
        beams.add(beam);
    }

    public void removeLater(Entity entity) {
        remove.add(entity);
    }
}
