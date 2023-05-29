/**
 * Name: Sukrit Desai
 * Teacher: Ms Krasteva
 * Description: Class for the final level that runs the platformer game.
 * */

package com.git.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class PlatformerGame extends ApplicationAdapter {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final float GRAVITY = -1000f;
    private static final float MOVE_SPEED = 200f;
    private static final float JUMP_FORCE = 500f;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TextureRegion playerTexture;
    private float playerX, playerY;
    private float playerVelocityX, playerVelocityY;
    private boolean isJumping;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        playerTexture = new TextureRegion(new Texture(Gdx.files.internal("character.png")));
        playerX = 100;
        playerY = 100;

        tiledMap = new TmxMapLoader().load("level.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    @Override
    public void render() {
        updatePlayer(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        batch.begin();
        batch.draw(playerTexture, playerX, playerY);
        batch.end();
    }

    private void updatePlayer(float deltaTime) {
        playerVelocityX = 0;

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            playerVelocityX = -MOVE_SPEED;
        } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            playerVelocityX = MOVE_SPEED;
        }

        if (Gdx.input.isKeyJustPressed(Keys.SPACE) && !isJumping) {
            playerVelocityY = JUMP_FORCE;
            isJumping = true;
        }

        playerVelocityY += GRAVITY * deltaTime;

        playerX += playerVelocityX * deltaTime;
        playerY += playerVelocityY * deltaTime;

        if (playerY <= 0) {
            playerY = 0;
            playerVelocityY = 0;
            isJumping = false;
        }

        // Collision detection and response with platforms
        // You'll need to implement this part yourself based on your level design
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        playerTexture.getTexture().dispose();
        tiledMap.dispose();
    }
}
