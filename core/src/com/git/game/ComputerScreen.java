package com.git.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import static com.git.game.GitOdyssey.gameSkin;

public class ComputerScreen implements Screen {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    private Stage stage;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private TextField consoleTextField;
    Label output;


    @Override
    public void show() {
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        // Create and position the rectangles in the left half of the screen
        float rectangleWidth = SCREEN_WIDTH / 5f;
        float rectangleX = 1f;
        float rectangleY = 1f;
        stage.addActor(new Rectangle(rectangleX, rectangleY, rectangleWidth, rectangleWidth, com.badlogic.gdx.graphics.Color.RED));

        // Create and position the instructions label in the top right
        Label instructionsLabel = new Label("Instructions:\nPlease clone the\nrepository", gameSkin, "default");
        instructionsLabel.setPosition(SCREEN_WIDTH / 2f, SCREEN_HEIGHT - instructionsLabel.getHeight());

        // Create and position the console text area in the bottom right
        float consoleWidth = SCREEN_WIDTH / 2f;
        float consoleHeight = SCREEN_HEIGHT / 2f;
        float consoleX = SCREEN_WIDTH / 2f;
        float consoleY = 0f;
        consoleTextField = new TextField("", gameSkin, "default");
        consoleTextField.setSize(consoleWidth, consoleHeight);
        consoleTextField.setPosition(consoleX, consoleY);
        output = new Label("", gameSkin, "default");
        output.setPosition(100, 100);
        output.setSize(20, 20);
        output.setColor(Color.BLACK);

        // Add elements to the stage
        stage.addActor(instructionsLabel);
        stage.addActor(consoleTextField);
        stage.addActor(output);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        // Check if the user presses enter in the console text field
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            output.setText("This is to be completed");
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
        stage.dispose();
    }
}
