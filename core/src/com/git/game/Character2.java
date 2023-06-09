package com.git.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import static com.git.game.FinalLevel.*;

/**
 * Description: Class for the character in the platformer game.
 *
 * <h2>Course info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Kevin Kolyakov
 * @version 6.9.23
 */

public class Character2 {
    /** velocity in the y direction */
    public float velocityY = 0;

    /** gravity constant */
    private static final float GRAVITY = -1000f;

    /** jump velocity */
    private static final float JUMP_VELOCITY = 700f;

    /** boolean to check if the character is on the ground */
    public boolean grounded = true;

    /** sprite batch */
    private final SpriteBatch batch;

    /** health of the character */
    public static int health = 3;

    /** texture of the character */
    private final Texture character;

    /** texture region of the character */
    private final TextureRegion[][] frames;

    /** animation of the character */
    private Animation<TextureRegion> animation;

    /** state time of the character */
    private float stateTime;

    /** row of the character sprite (changes the direction the character is facing) */
    private int row;

    /** x and y position of the character */
    public float x, y;

    /** old x and y position of the character before updating position */
    public float oldX, oldY;

    /**
     * Constructor for the character
     * @param sheetName name of the sprite sheet
     */
    public Character2(String sheetName) {
        // Set initial position
        x = 100;
        y = 150;

        // Load character sprite sheet
        character = new Texture(sheetName);

        // Split sprite sheet into texture regions
        int FRAME_COLS = 9;
        int FRAME_ROWS = 4;

        // Create 2D array of texture regions
        frames = TextureRegion.split(character, character.getWidth() / FRAME_COLS, character.getHeight() / FRAME_ROWS);
        stateTime = 0f;
        batch = new SpriteBatch();
        row = 0;
        stateTime = 0f;
    }

    /**
     * Gets user input and updates the character position
     * @param delta The time in seconds since the last render
     */
    public void update(float delta) {
        // Save old position
        oldX = x;
        oldY = y;

        // Set speed
        float speed = 200f;

        // Update state time
        stateTime += delta;

        // Reset position delta
        float deltaX = 0;

        // Update character movement based on key presses
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {//if the left key is pressed, the character moves left
            row = 1;
            deltaX -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {//if the right key is pressed, the character moves right
            row = 3;
            deltaX += speed * delta;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && grounded) {//if the up key is pressed and the character is on the ground, the character jumps
            row = 0;
            velocityY = JUMP_VELOCITY;
            grounded = false;
        }

        // Apply gravity
        velocityY += GRAVITY * delta;

        // Update character position
        x += deltaX;
        y += velocityY * delta;

        // Check for collisions
        checkCollision(x, y);

        //If the character goes off the screen, the user completes a multiple choice question
        if (x < -100) {
            game.setScreen(new Popup(game, new MultipleChoice(game), "Your speed fell short!\nTo prevent losing a heart, tackle the\nmultiple-choice question and complete it successfully!"));
        }

        // Update animation
        TextureRegion[] walkFrames = frames[row];
        animation = new Animation<>(0.25f, walkFrames);
    }


    /**
     * Renders the character
     */
    public void render() {
        // Update state time
        stateTime += Gdx.graphics.getDeltaTime();

        // Clear screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw animation of character
        batch.begin();
        batch.draw(animation.getKeyFrame(stateTime, true), Math.round(x), Math.round(y));
        batch.end();
    }

    /**
     * Disposes of the character and the sprite batch
     */
    public void dispose() {
        batch.dispose();
        character.dispose();
    }

    /**
     * Checks if the character's x and y coordinates are colliding with a tile
     * @param x The x coordinate of the character
     * @param y The y coordinate of the character
     */
    private void checkCollision(float x, float y) {
        //Checks for spikes collision
        if (getTileIdSpikes(x+18, y+32) != -1||getTileIdSpikes(x+40, y+32) != -1|| getTileIdSpikes(x+18, y+12) != -1||getTileIdSpikes(x+40, y+12) != -1){
            game.setScreen(new Popup(game, new MultipleChoice(game), "You have hit a spike!\nTo prevent losing a heart, tackle the\nmultiple-choice question and complete it successfully!"));
        }

        //Checks for ghost collision
        if (getTileIdGhost(x+18, y+32) != -1||getTileIdGhost(x+40, y+32) != -1|| getTileIdGhost(x+18, y+12) != -1||getTileIdGhost(x+40, y+12) != -1){
            game.setScreen(new Popup(game, new MultipleChoice(game), "A ghost has caught you!\nTo prevent losing a heart, tackle the\nmultiple-choice question and complete it successfully!"));
        }

        //Checks for if the character reaches the exit
        if (getTileIdExit(x+18, y+32) != -1||getTileIdExit(x+40, y+32) != -1|| getTileIdExit(x+18, y+12) != -1||getTileIdExit(x+40, y+12) != -1){
            game.setScreen(new TransitionAnimation(game, new MainMenu(game), "Congratulations on completing the final level and mastering Git!\nYour skill and determination have paid off.\nEnjoy your well-deserved victory!\nThank you for playing our game!\nWe appreciate your time and hope you enjoyed the experience."));
        }

        //Checks for if the character is colliding with a wall on x axis
        if (getTileId(x+15, y+40) != -1 || getTileId(x+51, y+40) != -1){
            this.x = oldX;
        }

        //Checks for if the character is colliding with a wall on y axis above
        if (getTileId(x+18, y+40) != -1 || getTileId(x+48, y+40) != -1){
            velocityY = 0;
        }

        //Checks for if the character is colliding with a wall on y axis below
        if (getTileId(x+18, y+12) != -1||getTileId(x+48, y+12) != -1) {
            this.y = oldY;
            velocityY = 0;
            grounded = true;
        }

        //Checks for if the character is colliding with a hole tile type
        if (getTileId(x+18, y+40) == 40||getTileId(x+48, y+40) == 40){
            game.setScreen(new Popup(game, new MultipleChoice(game), "You have fallen down a hole!\nTo prevent losing a heart, tackle the\nmultiple-choice question and complete it successfully!"));
        }
    }

    /**
     * Gets the tile id at the given x and y coordinates
     * @param x The x coordinate of the tile
     * @param y The y coordinate of the tile
     * @return The tile id of the tile at the given x and y coordinates or -1 if no tiles are found
     */
    private int getTileId(float x, float y) {
        int tileX = (int) ((x+cameraMovedCount) / FinalLevel.collisionLayer.getTileWidth());
        int tileY = (int) (y / collisionLayer.getTileHeight());

        //gets the cell at the given x and y coordinates by dividing the x and y coordinates by the tile width and height
        TiledMapTileLayer.Cell cell = collisionLayer.getCell(tileX, tileY);
        if (cell != null && cell.getTile() != null) {//checks if the cell is not null and the tile is not null
            return cell.getTile().getId()-1;//returns the tile id of the cell -1 since the tile id starts at 1 and the array starts at 0
        }
        return -1;
    }

    /**
     * Gets the tile id of the spike layer at the given x and y coordinates
     * @param x The x coordinate of the tile
     * @param y The y coordinate of the tile
     * @return The tile id of the tile at the given x and y coordinates or -1 if no tiles are found
     */
    private int getTileIdSpikes(float x, float y) {
        int tileX = (int) ((x+cameraMovedCount) / spikeLayer.getTileWidth());
        int tileY = (int) (y / spikeLayer.getTileHeight());

        //gets the cell at the given x and y coordinates by dividing the x and y coordinates by the tile width and height
        TiledMapTileLayer.Cell cell = spikeLayer.getCell(tileX, tileY);
        if (cell != null && cell.getTile() != null) {//checks if the cell is not null and the tile is not null
            return cell.getTile().getId();//returns the tile id of the cell
        }
        return -1;
    }

    /**
     * Gets the tile id of the exit layer at the given x and y coordinates
     * @param x The x coordinate of the tile
     * @param y The y coordinate of the tile
     * @return The tile id of the tile at the given x and y coordinates or -1 if no tiles are found
     */
    private int getTileIdExit(float x, float y) {
        int tileX = (int) ((x+cameraMovedCount) / exitLayer.getTileWidth());
        int tileY = (int) (y / exitLayer.getTileHeight());

        //gets the cell at the given x and y coordinates by dividing the x and y coordinates by the tile width and height
        TiledMapTileLayer.Cell cell = exitLayer.getCell(tileX, tileY);
        if (cell != null && cell.getTile() != null) {//checks if the cell is not null and the tile is not null
            return cell.getTile().getId();//returns the tile id of the cell
        }
        return -1;
    }

    /**
     * Gets the tile id of the ghost layer at the given x and y coordinates
     * @param x The x coordinate of the tile
     * @param y The y coordinate of the tile
     * @return The tile id of the tile at the given x and y coordinates or -1 if no tiles are found
     */
    private int getTileIdGhost(float x, float y) {
        int tileX = (int) ((x+cameraMovedCount) / ghostLayer.getTileWidth());
        int tileY = (int) (y / ghostLayer.getTileHeight());

        //gets the cell at the given x and y coordinates by dividing the x and y coordinates by the tile width and height
        TiledMapTileLayer.Cell cell = ghostLayer.getCell(tileX, tileY);
        if (cell != null && cell.getTile() != null) {//checks if the cell is not null and the tile is not null
            return cell.getTile().getId();//returns the tile id of the cell
        }
        return -1;
    }
}