package com.git.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * The splash screen is the first screen that is displayed when the game is started.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Sukrit Desai
 * @version 05.23.23
 * Time Spent: 1 hour
 */
public class SplashScreen implements Screen {

    /** The stage that holds the splash screen elements */
    private final Stage stage;

    /** The texture of the splash screen image */
    private final Texture splashImageTexture;

    /** The loading bar that shows the progress of the loading process */
    private final ProgressBar loadingBar;

    /** The progress of the loading process */
    private float progress;

    /**
     * Creates a new splash screen.
     */
    public SplashScreen() {
        // Create the stage and set it as the input processor
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        // Load the splash screen image and add it to the stage
        splashImageTexture = new Texture(Gdx.files.internal("git-logo.png"));
        Image splashImage = new Image(new TextureRegion(splashImageTexture));

        // Position the splash image in the center of the screen
        splashImage.setPosition(stage.getWidth() / 2 - splashImage.getWidth() / 2,
                stage.getHeight() / 2 - splashImage.getHeight() / 2);

        // Create the loading bar and position it below the splash image
        loadingBar = new ProgressBar(0f, 1f, 0.1f, false, new Skin(Gdx.files.internal("Skin/uiskin.json")), "default-horizontal");
        loadingBar.setSize(stage.getWidth() * 0.5f, 20f);
        loadingBar.setPosition(stage.getWidth() / 2 - loadingBar.getWidth() / 2, splashImage.getY() - loadingBar.getHeight() - 10f);

        // Set the loading bar animation speed
        loadingBar.setAnimateDuration(0.25f);

        // Add the splash image and loading bar to the stage
        stage.addActor(splashImage);
        stage.addActor(loadingBar);
    }

    @Override
    public void show() {}

    /**
     * Renders the splash screen.
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        // Clear the screen with a dark blue color
        Gdx.gl.glClearColor(21/255f, 30/255f, 43/255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the loading bar progress
        progress += delta / 4;
        loadingBar.setValue(progress);

        // Update and draw the stage
        stage.act(delta);
        stage.draw();

        // If the loading is complete, dispose of the splash screen and switch to the main menu screen
        if (progress >= 1f) {
            dispose();
            GitOdyssey game = (GitOdyssey) Gdx.app.getApplicationListener();
            game.setScreen(new MainMenu(game));
        }
    }

    @Override
    public void resize(int width, int height) {stage.getViewport().update(width, height, true);}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        splashImageTexture.dispose();
    }
}