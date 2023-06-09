package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * A screen that displays a transition animation.
 *
 * <h2>Course info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Kevin Kolyakov
 * @version 05.29.23
 */
public class TransitionAnimation implements Screen {

    /** The time when the animation started. */
    private final long startTime;

    /** The starting and ending colors of the animation. */
    private final Color startColor = Color.BLACK;
    private final Color endColor = Color.WHITE;

    /** The Game object that manages screens. */
    private final Game game;

    /** The previous screen to return to after the animation. */
    private final Screen screen;

    /** The message to display in the popup screen after the animation. */
    private final String message;


    /**
     * Constructs an TransitionAnimation object.
     * @param aGame The Game object that manages screens.
     * @param aScreen The previous screen to return to after the animation.
     * @param aMessage The message to display in the popup screen after the animation.
     */
    public TransitionAnimation(Game aGame, Screen aScreen, String aMessage) {
        message = aMessage;
        screen = aScreen;
        game = aGame;
        startTime = TimeUtils.millis();
    }

    @Override
    public void show() {}


    /**
     * Called when the screen should render itself.
     * @param delta The time in seconds since the last render.
     */
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

    /**
     * Interpolates between two colors based on a progress value.
     * @param startColor The starting color.
     * @param endColor The ending color.
     * @param progress The progress value between 0 and 1.
     * @return The interpolated color.
     */
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
    public void dispose() {}
}
