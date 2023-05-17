package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Animation implements Screen {

    private final SpriteBatch batch;
    private final long startTime;
    private final Color startColor = Color.BLACK;
    private final Color endColor = Color.WHITE;
    private final Game game;
    private final Screen screen;
    private final String message;


    public Animation(Game aGame, Screen aScreen, String aMessage) {
        message = aMessage;
        screen = aScreen;
        game = aGame;
        batch = new SpriteBatch();
        startTime = TimeUtils.millis();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Calculate the current color based on the elapsed time
        float elapsed = (TimeUtils.millis() - startTime) / 1000f;
        // Total duration of the animation in seconds
        float duration = 3.0f;
        float progress = Math.min(elapsed / duration, 1.0f);
        Color currentColor = interpolateColor(startColor, endColor, progress);

        // Set the clear color to the current color
        Gdx.gl.glClearColor(currentColor.r, currentColor.g, currentColor.b, currentColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Check if the animation is finished
        if (elapsed >= duration) {
            game.setScreen(new Popup(game, screen, message));
        }
    }

    private Color interpolateColor(Color startColor, Color endColor, float progress) {
        float r = startColor.r + (endColor.r - startColor.r) * progress;
        float g = startColor.g + (endColor.g - startColor.g) * progress;
        float b = startColor.b + (endColor.b - startColor.b) * progress;
        float a = startColor.a + (endColor.a - startColor.a) * progress;
        return new Color(r, g, b, a);
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
        batch.dispose();
    }
}
