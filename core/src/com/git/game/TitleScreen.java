package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TitleScreen implements Screen {

    private Stage stage;
    private Game game;

    public TitleScreen(Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());

        Texture texture = new Texture(Gdx.files.internal("Git-Icon-Black.png"));
        SpriteBatch batch = new SpriteBatch();


        Label title = new Label("Git Odyssey", GitOdyssey.gameSkin,"default");
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*2/3);
        title.setWidth(Gdx.graphics.getWidth());
        stage.addActor(title);

        TextButton introButton = new TextButton("Go to intro level!",GitOdyssey.gameSkin);
        introButton.setWidth(Gdx.graphics.getWidth()/2);
        introButton.setPosition(Gdx.graphics.getWidth()/2-introButton.getWidth()/2,Gdx.graphics.getHeight()/2-introButton.getHeight()/2);
        introButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new IntroScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(introButton);

        TextButton finalButton = new TextButton("Go to final level!",GitOdyssey.gameSkin);
        finalButton.setWidth(Gdx.graphics.getWidth()/2);
        finalButton.setPosition(Gdx.graphics.getWidth()/2-finalButton.getWidth()/2,Gdx.graphics.getHeight()/4-finalButton.getHeight()/2);
        finalButton.addListener(new InputListener(){
                @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(finalButton);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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
        stage.dispose();
    }
}