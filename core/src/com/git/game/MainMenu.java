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

public class MainMenu implements Screen {

    private final Stage stage;
    private final Game game;

    public MainMenu(Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());

        Label title = new Label("Git Odyssey", GitOdyssey.gameSkin,"default");
        title.setAlignment(Align.center);
        title.setY((float) (Gdx.graphics.getHeight()*2/3.0));
        title.setWidth(Gdx.graphics.getWidth());
        title.setFontScale(3);
        stage.addActor(title);

        TextButton introButton = new TextButton("Go to intro level!",GitOdyssey.gameSkin);
        introButton.setWidth((float) (Gdx.graphics.getWidth()/3.0));
        introButton.setHeight((float) (Gdx.graphics.getHeight()/10.0));
        introButton.setPosition((float) (Gdx.graphics.getWidth()/2.0)-introButton.getWidth()/2, ((float)(Gdx.graphics.getHeight()/2)-introButton.getHeight()/2));
        introButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Animation(game, new LearningLevel()));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(introButton);

        TextButton finalButton = new TextButton("Go to game!",GitOdyssey.gameSkin);
        finalButton.setWidth((float)Gdx.graphics.getWidth()/3);
        finalButton.setHeight((float)Gdx.graphics.getHeight()/10);
        finalButton.setPosition((float)Gdx.graphics.getWidth()/2-finalButton.getWidth()/2,(float)Gdx.graphics.getHeight()/4-finalButton.getHeight()/2);
        finalButton.addListener(new InputListener(){
                @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Animation(game, new GameScreen()));
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
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
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