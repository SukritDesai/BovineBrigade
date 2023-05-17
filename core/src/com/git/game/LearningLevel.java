package com.git.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class LearningLevel extends ApplicationAdapter implements Screen {
    private static final int ROOM_WIDTH = 800;
    private static final int ROOM_HEIGHT = 600;
    private static final int CHARACTER_SIZE = 64;
    private static final int COMPUTER_SIZE = 128;

    private SpriteBatch batch;
    private Texture characterTexture;
    private Texture computerTexture;
    private OrthographicCamera camera;
    private Rectangle character;
    private Rectangle computer;
    private boolean isInteracting;

    @Override
    public void create() {
        batch = new SpriteBatch();
        characterTexture = new Texture("character.png");
        computerTexture = new Texture("computer.png");

        // Set up camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ROOM_WIDTH, ROOM_HEIGHT);

        // Set up character
        character = new Rectangle();
        character.setSize(CHARACTER_SIZE, CHARACTER_SIZE);
        character.setPosition(ROOM_WIDTH / 2.0f - CHARACTER_SIZE / 2.0f, ROOM_HEIGHT / 2.0f - CHARACTER_SIZE / 2.0f);

        // Set up computer
        computer = new Rectangle();
        computer.setSize(COMPUTER_SIZE, COMPUTER_SIZE);
        computer.setPosition(ROOM_WIDTH / 2.0f - COMPUTER_SIZE / 2.0f, ROOM_HEIGHT / 2.0f - COMPUTER_SIZE / 2.0f - 100);
    }

    @Override
    public void render() {
        handleInput();
        update();
        draw();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            character.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            character.x += 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            character.y += 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            character.y -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (character.overlaps(computer)) {
                isInteracting = true;
                // Open the computer screen
                // Implement your own logic here
            }
        }
    }

    private void update() {
        camera.update();
        if (!isInteracting) {
            // Clamp character movement within the room bounds
            if (character.x < 0) {
                character.x = 0;
            }
            if (character.x > ROOM_WIDTH - CHARACTER_SIZE) {
                character.x = ROOM_WIDTH - CHARACTER_SIZE;
            }
            if (character.y < 0) {
                character.y = 0;
            }
            if (character.y > ROOM_HEIGHT - CHARACTER_SIZE) {
                character.y = ROOM_HEIGHT - CHARACTER_SIZE;
            }
        }
    }

    private void draw() {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // Draw character
        batch.draw(characterTexture, character.x, character.y);

        // Draw computer
        batch.draw(computerTexture, computer.x, computer.y);

        batch.end();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void hide() {

    }
}
