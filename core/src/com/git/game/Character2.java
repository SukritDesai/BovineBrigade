package com.git.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import static com.git.game.FinalLevel.collisionLayer;

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
    public float x = 100, y = 200;
    public float oldX, oldY;

    public Character2(String sheetName) {
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
        if (checkCollisionSide(x+5, y+10) == 41 || checkCollisionSide(x-5, y) == 41){
            this.x = oldX;
        }
        if (checkCollisionSide(x, y-15) != -1){
            velocityY = 0;
        }
        if (checkCollisionSide(x, y+12) != -1){
            this.y = oldY;
            grounded = true;
        }

    }

    private static int checkCollisionSide(float x, float y) {
        int tileX = (int) (x / collisionLayer.getTileWidth());
        int tileY = (int) (y / collisionLayer.getTileHeight());

        // Check if the character's bottom collides with any solid tile
        TiledMapTileLayer.Cell cell = collisionLayer.getCell(tileX, tileY);
        if (cell != null && cell.getTile() != null && cell.getTile().getId() != 40) {
            return cell.getTile().getId(); // Collision detected
        }

        return -1; // No collision
    }

}