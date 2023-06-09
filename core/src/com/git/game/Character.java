/**
 * Represents a character in the game.
 * The character is controlled by user input and rendered on the screen.
 *
 * @author Sukrit Desai
 */

package com.git.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

import static com.git.game.Maze.characterX;
import static com.git.game.Maze.characterY;

public class Character {
    private final SpriteBatch batch; // Used for rendering sprites
    private final Texture character; // The texture of the character
    private final TextureRegion[][] frames; // A 2D array of texture regions for character animation frames
    private Animation<TextureRegion> animation; // The animation for the character
    private float stateTime; // The time elapsed since the start of the animation
    private int row; // The current row index in the frames array
    public float x = 100, y = 100; // The position of the character

    /**
     * Constructs a new Character object.
     *
     * @param sheetName the file path of the character sprite sheet
     */
    public Character(String sheetName) {
        character = new Texture(sheetName);
        int FRAME_COLS = 9; // The number of columns in the sprite sheet
        int FRAME_ROWS = 4; // The number of rows in the sprite sheet
        frames = TextureRegion.split(character, character.getWidth() / FRAME_COLS, character.getHeight() / FRAME_ROWS);
        stateTime = 0f;
        batch = new SpriteBatch();
        row = 0;
        stateTime = 0f;
    }

    /**
     * Updates the character's state based on the elapsed time.
     *
     * @param delta the time elapsed since the last update
     */
    public void update(float delta) {
        stateTime += delta;

        // Update character movement based on key presses
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            row = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            row = 3;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            row = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            row = 2;
        }

        // Update character position
        x = characterX;
        y = characterY;
        TextureRegion[] walkFrames = frames[row];
        animation = new Animation<>(0.25f, walkFrames);
    }

    /**
     * Renders the character on the screen.
     * Clears the screen and draws the character sprite using the animation.
     */
    public void render() {
        stateTime += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(animation.getKeyFrame(stateTime, true), Math.round(x), Math.round(y));
        batch.end();
    }

    /**
     * Disposes of the resources used by the character.
     * Disposes the sprite batch and character texture.
     */
    public void dispose() {
        batch.dispose();
        character.dispose();
    }
}