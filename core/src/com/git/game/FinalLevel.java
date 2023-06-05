/**
 * Name: Sukrit Desai
 * Teacher: Ms Krasteva
 * Description: Class for the final level that runs the platformer game.
 * */

package com.git.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FinalLevel implements Screen {
    private Character character;

    public FinalLevel() {
        character = new Character();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        character.update(delta);
        character.render();
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