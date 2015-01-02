package io.github.alyphen.nygj;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import io.github.alyphen.nygj.screen.GameScreen;
import io.github.alyphen.nygj.screen.MainMenuScreen;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;

public class NYGJ extends Game {

	private SpriteBatch spriteBatch;
    private BitmapFont font;

    private Animation waterAnimation;
    private Animation grassAnimation;
    private Animation rockAnimation;
    private Animation lilyPadAnimation;
    private Animation treeAnimation;
    private Animation towerAnimation;

    private Animation enemyAnimation;

    private Animation wizardWalkDown;
    private Animation wizardWalkLeft;
    private Animation wizardWalkUp;
    private Animation wizardWalkRight;
    private Animation wizardShootUp;
    private Animation wizardShootLeft;
    private Animation wizardShootDown;
    private Animation wizardShootRight;

    private TextureRegion beam;

    private Music menuMusic;
    private Music ingameMusic;
	
	@Override
	public void create () {
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("nygj.ogg"));
        ingameMusic = Gdx.audio.newMusic(Gdx.files.internal("nygj_battle.ogg"));
		spriteBatch = new SpriteBatch();
        font = new BitmapFont();

        Texture tileSheet = new Texture(Gdx.files.internal("tiles.png"));
        waterAnimation = getAnimation(tileSheet, 0, 0, 32, 32, 8, 0.2F);
        grassAnimation = getAnimation(tileSheet, 0, 32, 32, 32, 4, 0.2F);
        rockAnimation = getAnimation(tileSheet, 128, 32, 32, 32, 4, 0.2F);
        lilyPadAnimation = getAnimation(tileSheet, 0, 64, 32, 32, 8, 0.2F);
        treeAnimation = getAnimation(tileSheet, 0, 96, 64, 64, 4, 0.2F);
        towerAnimation = getAnimation(tileSheet, 0, 160, 64, 96, 4, 0.2F);

        Texture enemySheet = new Texture(Gdx.files.internal("enemy.png"));
        enemyAnimation = getAnimation(enemySheet, 0, 0, 32, 32, 1, 0.2F);

        Texture wizardSheet = new Texture(Gdx.files.internal("wizard.png"));
        wizardWalkDown = getAnimation(wizardSheet, 0, 0, 32, 32, 4, 0.2F);
        wizardWalkLeft = getAnimation(wizardSheet, 0, 32, 32, 32, 4, 0.2F);
        wizardWalkUp = getAnimation(wizardSheet, 0, 64, 32, 32, 4, 0.2F);
        wizardWalkRight = getAnimation(wizardSheet, 0, 96, 32, 32, 4, 0.2F);
        wizardShootUp = getAnimation(wizardSheet, 0, 128, 32, 32, 4, 0.2F);
        wizardShootLeft = getAnimation(wizardSheet, 0, 160, 32, 32, 4, 0.2F);
        wizardShootDown = getAnimation(wizardSheet, 0, 192, 32, 32, 4, 0.2F);
        wizardShootRight = getAnimation(wizardSheet, 0, 224, 32, 32, 4, 0.2F);

        Texture beamSheet = new Texture(Gdx.files.internal("beam.png"));
        beam = new TextureRegion(beamSheet, 0, 0, 32, 32);
        setScreen(new MainMenuScreen(this));
	}

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public BitmapFont getFont() {
        return font;
    }

    private Animation getAnimation(Texture sheet, int x, int y, int width, int height, int frameAmount, float frameDelay) {
        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < frameAmount; i++) {
            TextureRegion region = new TextureRegion(sheet, x + (width * i), y, width, height);
            region.flip(false, true);
            frames.add(region);
        }
        Animation animation = new Animation(frameDelay, frames);
        animation.setPlayMode(LOOP);
        return animation;
    }

    public Animation getHeroAnimation(String hero, String direction, boolean shoot) {
        switch (hero) {
            case "wizard":
                switch (direction) {
                    case "down": return shoot  ? wizardShootDown : wizardWalkDown;
                    case "left": return shoot ? wizardShootLeft : wizardWalkLeft;
                    case "up": return shoot ? wizardShootUp : wizardWalkUp;
                    case "right": return shoot ? wizardShootRight : wizardWalkRight;
                    default: return null;
                }
            default: return null;
        }
    }

    public Animation getAnimation(String name) {
        switch (name) {
            case "water": return waterAnimation;
            case "grass": return grassAnimation;
            case "rock": return rockAnimation;
            case "lilypad": return lilyPadAnimation;
            case "tree": return treeAnimation;
            case "tower": return towerAnimation;
            case "enemy": return enemyAnimation;
            default: return null;
        }
    }

    public Music getMenuMusic() {
        return menuMusic;
    }

    public Music getIngameMusic() {
        return ingameMusic;
    }

    @Override
    public void dispose() {
        super.dispose();
        spriteBatch.dispose();
        font.dispose();
        waterAnimation.getKeyFrames()[0].getTexture().dispose();
        enemyAnimation.getKeyFrames()[0].getTexture().dispose();
        wizardWalkDown.getKeyFrames()[0].getTexture().dispose();
        beam.getTexture().dispose();
        ingameMusic.dispose();
        menuMusic.dispose();
    }

    public TextureRegion getBeam() {
        return beam;
    }
}
