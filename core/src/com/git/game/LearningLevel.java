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

public class LearningLevel extends Game {
    public static final int ROOM_WIDTH = 1280;
    public static final int ROOM_HEIGHT = 720;
    public static final int CHARACTER_SIZE = 64;
    public static final int COMPUTER_SIZE = 128;

    public SpriteBatch batch;
    public Texture characterTexture;
    public Texture computerTexture;
    public OrthographicCamera camera;
    public Rectangle character;
    public Rectangle computer;
    public boolean isInteracting;

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

        setScreen(new RoomScreen());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
