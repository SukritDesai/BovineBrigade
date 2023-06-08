package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Maze implements Screen {
    private final Game game;
    private final OrthographicCamera cam;
    private final float rotationSpeed;
    private final TiledMap map;
    private final Character character;
    private final TiledMapRenderer tiledMapRenderer;

    public Maze(Game game) {
        this.game = game;
        rotationSpeed = 0.5f;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(30, 30 * (h / w));
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
        // cam = new OrthographicCamera();
        cam.setToOrtho(false, 1280, 720);

        map = new TmxMapLoader().load("MazeMap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        character = new Character("character.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Apply camera matrices
        cam.update();
        character.batch.setProjectionMatrix(cam.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render the map
        tiledMapRenderer.setView(cam);
        tiledMapRenderer.render();

        // Update and render the character
        character.update(delta);
        character.render();
        handleInput();

        // Update the camera position and zoom
        cam.position.set(character.x, character.y, 0);
        cam.zoom = MathUtils.clamp(65 / cam.viewportWidth, 0.1f, 100f);
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.zoom += 0.02;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom -= 0.02;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-5, 0, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(5, 0, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -5, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 5, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cam.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            cam.rotate(rotationSpeed, 0, 0, 1);
        }

        cam.zoom = MathUtils.clamp(cam.zoom, 0.01f, 100/cam.viewportWidth);

        float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 1280 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 720 - effectiveViewportHeight / 2f);
    }

    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = 30f;
        cam.viewportHeight = 30f * height/width;
        cam.update();
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
        map.dispose();
        character.dispose();
    }
}
