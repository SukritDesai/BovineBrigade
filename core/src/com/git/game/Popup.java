/**
 * Name: Kevin Kolyakov
 * Teacher: Ms Krasteva
 * Description: Class for text pop-up between screens for instructions.
 */

package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;

import static com.git.game.GitOdyssey.gameSkin;

public class Popup implements Screen {

    private final Stage stage;
    private final long startTime;
    private final Game game;
    private final Screen screen;
    private final String message;

    public Popup(Game aGame, Screen aScreen, String aMessage) {
        message = aMessage;
        screen = aScreen;
        game = aGame;
        stage = new Stage();
        startTime = TimeUtils.millis();

        // Create the popup content
        Table table = new Table();
        table.setFillParent(true);

        Label popupLabel = new Label(message+"\nPress enter to continue", gameSkin);
        table.add(popupLabel);

        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update and render the stage
        stage.act(delta);
        stage.draw();

        // Check if the popup has finished displaying
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            // Transition to the game screen
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
