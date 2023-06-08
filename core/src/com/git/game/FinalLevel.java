/**
 * Name: Sukrit Desai
 * Teacher: Ms Krasteva
 * Description: Class for the final level that runs the platformer game.
 * */


/**
 * Todo:
 * - Add a way to win the game- Completed Kevin
 * - Change map to have spikes/collision with spikes - Completed Kevin
 * - Comment code
 * - Possibly add an enemy character with collision
 * - Generate JavaDoc
 */

package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


public class FinalLevel implements Screen {
    static TiledMap map = new TmxMapLoader().load("GameMap.tmx");
    public final OrthogonalTiledMapRenderer tiledMapRenderer;
    private final Character2 character;
    public static final OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());;
    private SpriteBatch spriteBatch = new SpriteBatch();
    private final Texture imageTexture = new Texture("Heart.png");
    public static TiledMapTileLayer collisionLayer = (TiledMapTileLayer) map.getLayers().get(0);
    public static TiledMapTileLayer spikeLayer = (TiledMapTileLayer) map.getLayers().get("Spikes");
    public static TiledMapTileLayer exitLayer = (TiledMapTileLayer) map.getLayers().get("Exit");
    public static TiledMapTileLayer holeLayer = (TiledMapTileLayer) map.getLayers().get("Hole");

    public static TiledMapTileLayer ghostLayer = (TiledMapTileLayer) map.getLayers().get("Ghosts");
    public static Game game;
    public static int cameraMovedCount = 0;


    public FinalLevel(Game game) {
        cameraMovedCount = 0;
        collisionLayer = (TiledMapTileLayer) map.getLayers().get(0);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        camera.position.set(screenWidth / 2, screenHeight / 2, 0);
        camera.update();
        character = new Character2("character.png");
        this.game = game;
    }


    private void updateCamera() {
        camera.position.x++;
        cameraMovedCount++;
        character.x--;
        camera.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        updateCamera();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        character.update(delta);
        character.render();



        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();


        spriteBatch.begin();
        if (character.health==1) {
            spriteBatch.draw(imageTexture, 0, 0);
        } else if (character.health==2) {
            spriteBatch.draw(imageTexture, 0, 0);
            spriteBatch.draw(imageTexture, 50, 0);
        } else if (character.health==3) {
            spriteBatch.draw(imageTexture, 0, 0);
            spriteBatch.draw(imageTexture, 50, 0);
            spriteBatch.draw(imageTexture, 100, 0);
        }
        spriteBatch.end();

        if(character.y<100){
            game.setScreen(new Popup(game, new MultipleChoice(game), "You have fallen, you must complete this question to not lose health\nIf you lose all 3 health you will be sent back to the main menu"));
        }

        camera.update();
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