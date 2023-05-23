/**
 * Name: Sukrit Desai
 * Teacher: Ms Krasteva
 * Description: Splash screen with logo and loading bar.
 */

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

public class SplashScreen implements Screen {

    private final Stage stage;
    private final Texture splashImageTexture;
    private final ProgressBar loadingBar;
    private float progress;

    public SplashScreen() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        splashImageTexture = new Texture(Gdx.files.internal("git-logo.png"));
        Image splashImage = new Image(new TextureRegion(splashImageTexture));
        splashImage.setPosition(stage.getWidth() / 2 - splashImage.getWidth() / 2,
                stage.getHeight() / 2 - splashImage.getHeight() / 2);

        loadingBar = new ProgressBar(0f, 1f, 0.1f, false, new Skin(Gdx.files.internal("Skin/uiskin.json")), "default-horizontal");
        loadingBar.setSize(stage.getWidth() * 0.5f, 20f);
        loadingBar.setPosition(stage.getWidth() / 2 - loadingBar.getWidth() / 2, splashImage.getY() - loadingBar.getHeight() - 10f);
        loadingBar.setAnimateDuration(0.25f);

        stage.addActor(splashImage);
        stage.addActor(loadingBar);
    }

    @Override
    public void show() {
        // Initialize any resources or start any loading processes here
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(21/255f, 30/255f, 43/255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the progress and move to the next screen when loading is complete
        progress += delta / 4; // Adjust the increment value as needed
        loadingBar.setValue(progress);

        stage.act(delta);
        stage.draw();

        if (progress >= 1f) {
            dispose();
            GitOdyssey game = (GitOdyssey) Gdx.app.getApplicationListener();
            game.setScreen(new MainMenu(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
        splashImageTexture.dispose();
    }
}