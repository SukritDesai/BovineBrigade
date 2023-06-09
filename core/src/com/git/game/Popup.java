package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import static com.git.game.GitOdyssey.gameSkin;

/**
 * Popup class that is used to display a message to the user and waits for the user to press enter to continue
 *
 * <h2>Course info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Kevin Kolyakov
 * @version 05.29.23
 * Time Spent: 1 hour
 */
public class Popup implements Screen {

    /** The stage that the popup is drawn on */
    private final Stage stage;

    /** The game that the popup is in */
    private final Game game;

    /** The screen that the game will go to after the popup is closed */
    private final Screen screen;

    /**
     * Constructor for the popup screen
     * @param aGame the game that the popup is in
     * @param aScreen the screen that the game will go to after the popup is closed
     * @param aMessage the message that will be displayed to the user
     */
    public Popup(Game aGame, Screen aScreen, String aMessage) {
        // Set the screen and game
        screen = aScreen;
        game = aGame;

        // Create the stage and table
        stage = new Stage();
        Table table = new Table();
        table.setFillParent(true);

        // Create the label and add it to the table
        Label popupLabel = new Label(aMessage +"\nPress enter to continue", gameSkin);
        table.add(popupLabel);

        // Add the table to the stage
        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Renders the popup screen with a message and using the enter key to continue
     * @param delta the time in seconds since the last render
     */
    @Override
    public void render(float delta) {
        // Set the background color to black and clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update and draw the stage
        stage.act(delta);
        stage.draw();

        // If the user presses enter, go to the next screen
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            game.setScreen(screen);
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
