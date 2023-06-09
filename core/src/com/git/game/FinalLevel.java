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

/**
 * Class that renders the final level of the game
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Sukrit Desai
 * @version 06.04.23
 * Time Spent: 2 hours
 *
 * Modifications History:
 * @author Kevin Kolyakov
 * @version 6.9.23
 * Time Spent: 1 hours
 * Modifications: Modified messages and add comments
 */
public class FinalLevel implements Screen {
    /** The map that is rendered */
    //Tiles in game map for floor and walls from: https://pixelfrog-assets.itch.io/kings-and-pigs
    static TiledMap map = new TmxMapLoader().load("GameMap.tmx");

    /** The renderer for the map */
    public final OrthogonalTiledMapRenderer tiledMapRenderer;

    /** The character that is rendered */
    private final Character2 character;

    /** The camera that is used to render the map */
    public static final OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    /** The sprite batch that is used to render the health bar */
    private final SpriteBatch spriteBatch = new SpriteBatch();

    /** The texture that is used to render the health bar */
    private final Texture imageTexture = new Texture("Heart.png");

    /** The collision layer of the map */
    public static TiledMapTileLayer collisionLayer = (TiledMapTileLayer) map.getLayers().get(0);

    /** The spike layer of the map */
    public static TiledMapTileLayer spikeLayer = (TiledMapTileLayer) map.getLayers().get("Spikes");

    /** The exit layer of the map */
    public static TiledMapTileLayer exitLayer = (TiledMapTileLayer) map.getLayers().get("Exit");

    /** The ghost layer of the map */
    public static TiledMapTileLayer ghostLayer = (TiledMapTileLayer) map.getLayers().get("Ghosts");

    /** The game that is rendered */
    public static Game game;

    /** The number of times the camera has moved */
    public static int cameraMovedCount = 0;


    /**
     * Constructor for the final level
     * @param game The game that is rendered
     */
    public FinalLevel(Game game) {
        cameraMovedCount = 0;//resets the camera moved count

        collisionLayer = (TiledMapTileLayer) map.getLayers().get(0);//resets the collision layer

        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);//creates the map renderer

        float screenWidth = Gdx.graphics.getWidth();//gets the screen width

        float screenHeight = Gdx.graphics.getHeight();//gets the screen height

        camera.position.set(screenWidth / 2, screenHeight / 2, 0);//sets the camera position

        camera.update();//updates the camera

        //Used https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?body=Body_color_light&head=Human_male_light to create character
        character = new Character2("character.png");//creates the character

        FinalLevel.game = game;//sets the game
    }

    /**
     * Updates the camera position and moves the character to the left
     */
    private void updateCamera() {
        //moves the camera to the right and character to the left to create side-scrolling effect
        camera.position.x++;
        cameraMovedCount++;
        character.x--;

        //updates the camera
        camera.update();
    }

    @Override
    public void show() {}

    /**
     * Renders the game and updates the camera position. Update the health bar based on the character's health value.
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        //updates the camera position
        updateCamera();

        //Clears screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //updates the character and renders it
        character.update(delta);
        character.render();

        //renders the map
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        //renders the health bar
        spriteBatch.begin();
        if (Character2.health ==1) {
            spriteBatch.draw(imageTexture, 0, 0);
        } else if (Character2.health ==2) {
            spriteBatch.draw(imageTexture, 0, 0);
            spriteBatch.draw(imageTexture, 50, 0);
        } else if (Character2.health ==3) {
            spriteBatch.draw(imageTexture, 0, 0);
            spriteBatch.draw(imageTexture, 50, 0);
            spriteBatch.draw(imageTexture, 100, 0);
        }
        spriteBatch.end();

        //checks if the character has fallen and displays a message
        if(character.y<100){
            game.setScreen(new Popup(game, new MultipleChoice(game), "You have fallen, you must complete this question to not lose health\nIf you lose all 3 health you will be sent back to the main menu"));
        }

        //updates camera
        camera.update();
    }


    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        //disposes of the character
        character.dispose();
    }
}