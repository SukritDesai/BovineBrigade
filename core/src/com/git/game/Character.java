package com.git.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Character {

    private final SpriteBatch batch;
    private final Texture character;
    private final TextureRegion[][] frames;
    private Animation<TextureRegion> animation;
    private float stateTime;
    private int row;
    public float x = 100, y = 100;

    public Character(String sheetName) {
        character = new Texture(sheetName);
        int FRAME_COLS = 9;
        int FRAME_ROWS = 4;
        frames = TextureRegion.split(character, character.getWidth() / FRAME_COLS, character.getHeight() / FRAME_ROWS);
        stateTime = 0f;
        batch = new SpriteBatch();
        row = 0;
        stateTime = 0f;
    }

    public void update(float delta) {
        float speed = 200f;

        stateTime += delta;

        // Reset position delta
        float deltaX = 0;
        float deltaY = 0;

        // Update character movement based on key presses
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            row = 1;
            deltaX -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            row = 3;
            deltaX += speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            row = 0;
            deltaY += speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            row = 2;
            deltaY -= speed * delta;
        }

        // Update character position
        x += deltaX;
        y += deltaY;
        TextureRegion[] walkFrames = frames[row];
        animation = new Animation<>(0.25f, walkFrames);
    }

    public void render() {
        stateTime += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(animation.getKeyFrame(stateTime, true), Math.round(x), Math.round(y));
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        character.dispose();
    }
}