/**
 * Name: Sukrit Desai
 * Teacher: Ms Krasteva
 * Description: Class for the final level that runs the platformer game.
 * */

package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import static com.git.game.Character2.health;

public class FinalLevel implements Screen {
    static TiledMap map = new TmxMapLoader().load("GameMap.tmx");
    public static final OrthogonalTiledMapRenderer tiledMapRenderer = new OrthogonalTiledMapRenderer(map);;
    private final Character2 character;
    private final OrthographicCamera camera;
    private SpriteBatch spriteBatch = new SpriteBatch();
    private final Texture imageTexture = new Texture("Heart.png");
    public static TiledMapTileLayer collisionLayer = (TiledMapTileLayer) map.getLayers().get(0);
    private Game game;

    public FinalLevel(Game game) {
        character = new Character2("character.png");
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        character.update(delta);
        character.render();

//        camera.translate(character.x-camera.position.x, 0);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();


        spriteBatch.begin();
        if (health==3) {
            spriteBatch.draw(imageTexture, 0, 0);
            spriteBatch.draw(imageTexture, 50, 0);
            spriteBatch.draw(imageTexture, 100, 0);
        } else if (health==2) {
            spriteBatch.draw(imageTexture, 0, 0);
            spriteBatch.draw(imageTexture, 50, 0);
        } else if (health==1) {
            spriteBatch.draw(imageTexture, 0, 0);
        }
        spriteBatch.end();

        if(character.y<100){
            game.setScreen(new Popup(game, new MultipleChoice(game), "You have fallen, you must complete this question to not lose health\nIf you lose all 3 health you will be sent back to the main menu"));
        }
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
        character.dispose();
    }
}