package com.git.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

import static com.git.game.FinalLevel.*;

public class Character2 {

    public float velocityY = 0;
    private static final float GRAVITY = -500f;
    private static final float JUMP_VELOCITY = 500f;
    public boolean grounded = true;
    private final SpriteBatch batch;
    public static int health = 3;
    private final Texture character;
    private final TextureRegion[][] frames;
    private Animation<TextureRegion> animation;
    private float stateTime;
    private int row;
    public float x, y;
    public float oldX, oldY;

    public Character2(String sheetName) {
        x = 100;
        y = 150;
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

        oldX = x;
        oldY = y;
        float speed = 200f;

        stateTime += delta;

        // Reset position delta
        float deltaX = 0;

        // Update character movement based on key presses
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            row = 1;

            deltaX -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            row = 3;
            deltaX += speed * delta;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && grounded) {
            row = 0;
            y+=1;
            velocityY = JUMP_VELOCITY;
            grounded = false;
        }

        // Apply gravity
        velocityY += GRAVITY * delta;

        // Update character position
        x += deltaX;

        y += velocityY * delta;

        checkCollision(x, y);
        if (x < 0) {
            x = 0;
        }


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
    private void checkCollision(float x, float y) {
        if (getTileId(x+15, y+32) != -1 || getTileId(x+51, y+32) != -1){
            this.x = oldX;
        }
        if (getTileId(x+18, y+62) != -1 || getTileId(x+48, y+62) != -1){
            velocityY = 0;
        }
        if (getTileId(x+18, y+12) == 40||getTileId(x+48, y+12) == 40){
            game.setScreen(new Popup(game, new MultipleChoice(game), "You have fallen down a hole!\nComplete the multiple choice question to not lose a heart!"));
        }
        if (getTileId(x+18, y+12) != -1||getTileId(x+48, y+12) != -1) {
            this.y = oldY;
            grounded = true;
        }
    }

    private int getTileId(float x, float y) {
        int tileX = (int) ((x+cameraMovedCount) / FinalLevel.collisionLayer.getTileWidth());
        int tileY = (int) (y / collisionLayer.getTileHeight());

        TiledMapTileLayer.Cell cell = collisionLayer.getCell(tileX, tileY);
        if (cell != null && cell.getTile() != null) {
            return cell.getTile().getId()-1;
        }
        return -1;
    }

}