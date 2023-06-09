package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * This class is the main menu of the game. It contains two buttons, one for the learning level and one for the game levels.
 *
 * <h2>Course info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Sukrit Desai
 * @version 05.23.23
 * Time Spent: 2 hours
 *
 * Modifications History:
 * @author Kevin Kolyakov
 * @version 6.9.23
 * Time Spent: 1 hours
 * Modifications: Added exit button
 */
public class MainMenu implements Screen {

    /** The stage that holds the buttons */
    private final Stage stage;

    /** The game that the screen is in */
    private final Game game;

    /**
     * Creates two buttons, one for the learning level and one for the game levels.
     * @param aGame
     */
    public MainMenu(Game aGame) {
        // Set up the stage
        game = aGame;
        stage = new Stage(new ScreenViewport());

        // Create the title
        Label title = new Label("Git Odyssey", GitOdyssey.gameSkin, "default");
        title.setAlignment(Align.center);
        title.setY((float) (Gdx.graphics.getHeight()*2/3.0));
        title.setWidth(Gdx.graphics.getWidth());
        title.setFontScale(3);
        stage.addActor(title);

        // Create the intro button
        TextButton introButton = new TextButton("Introduction",GitOdyssey.gameSkin);

        // Set the size and position of the button
        introButton.setWidth((float) (Gdx.graphics.getWidth()/3.0));
        introButton.setHeight((float) (Gdx.graphics.getHeight()/10.0));
        introButton.setPosition((float) (Gdx.graphics.getWidth()/2.0)-introButton.getWidth()/2, ((float)(Gdx.graphics.getHeight()/2)-introButton.getHeight()/2));
        introButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                // When the button is pressed, go to the learning level
                game.setScreen(new TransitionAnimation(game, new LearningLevel(game), "Embark on a thrilling journey to master Git!\nFollow instructions, complete tasks, and unlock\nits secrets in this immersive game.\nConquer challenges, learn collaboration, and become a Git expert.\nGet ready for an epic quest!"));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(introButton);

        // Create the button that skips the learning level
        TextButton finalButton = new TextButton("Play",GitOdyssey.gameSkin);

        // Set the size and position of the button
        finalButton.setWidth((float)Gdx.graphics.getWidth()/3);
        finalButton.setHeight((float)Gdx.graphics.getHeight()/10);
        finalButton.setPosition((float)Gdx.graphics.getWidth()/2-finalButton.getWidth()/2,(float)Gdx.graphics.getHeight()/4-finalButton.getHeight()/2+40);
        finalButton.addListener(new InputListener(){
                @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                // When the button is pressed, go to the game
                game.setScreen(new TransitionAnimation(game, new Maze(game, 3), "Test your Git knowledge in an exhilarating maze!\nUse arrow keys to navigate, conquer challenges, and reach the final level.\nProve your expertise and emerge victorious!"));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(finalButton);

        // Create the button that exits the game
        TextButton exitButton = new TextButton("Exit",GitOdyssey.gameSkin);

        // Set the size and position of the button
        exitButton.setWidth((float)Gdx.graphics.getWidth()/3);
        exitButton.setHeight((float)Gdx.graphics.getHeight()/10);
        exitButton.setPosition((float)Gdx.graphics.getWidth()/2-exitButton.getWidth()/2,(float)Gdx.graphics.getHeight()/4-exitButton.getHeight()/2-100);
        exitButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                // When the button is pressed, exit the game
                Gdx.app.exit();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(exitButton);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Renders the stage.
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        // Set the background color and clear the screen
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the stage
        stage.act();
        stage.draw();
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
        stage.dispose();
    }
}