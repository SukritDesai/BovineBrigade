package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class RoomScreen implements Screen {
    private final SpriteBatch batch;
    private final Texture characterTexture;
    private final Texture computerTexture;
    private final OrthographicCamera camera;
    private final Rectangle character;
    private final Rectangle computer;
    private boolean isInteracting;
    private final Game game;

    public RoomScreen(Game aGame) {
        game = aGame;
        batch = new SpriteBatch();


        characterTexture = new Texture("character.png");
        computerTexture = new Texture("computer.png");

        // Set up camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, LearningLevel.ROOM_WIDTH, LearningLevel.ROOM_HEIGHT);

        // Set up character
        character = new Rectangle();
        character.setSize(LearningLevel.CHARACTER_SIZE, LearningLevel.CHARACTER_SIZE);
        character.setPosition(LearningLevel.ROOM_WIDTH / 2.0f - LearningLevel.CHARACTER_SIZE / 2.0f, LearningLevel.ROOM_HEIGHT / 2.0f - LearningLevel.CHARACTER_SIZE / 2.0f);

        // Set up computer
        computer = new Rectangle();
        computer.setSize(LearningLevel.COMPUTER_SIZE, LearningLevel.COMPUTER_SIZE);
        computer.setPosition(LearningLevel.ROOM_WIDTH / 2.0f - LearningLevel.COMPUTER_SIZE / 2.0f-600, LearningLevel.ROOM_HEIGHT / 2.0f - LearningLevel.COMPUTER_SIZE / 2.0f - 100);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
                game.setScreen(new ComputerScreen());
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
            if (character.x > LearningLevel.ROOM_WIDTH - LearningLevel.CHARACTER_SIZE) {
                character.x = LearningLevel.ROOM_WIDTH - LearningLevel.CHARACTER_SIZE;
            }
            if (character.y < 0) {
                character.y = 0;
            }
            if (character.y > LearningLevel.ROOM_HEIGHT - LearningLevel.CHARACTER_SIZE) {
                character.y = LearningLevel.ROOM_HEIGHT - LearningLevel.CHARACTER_SIZE;
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
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        characterTexture.dispose();
        computerTexture.dispose();
    }
}
