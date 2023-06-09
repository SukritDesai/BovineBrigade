package com.git.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * A rectangle that can be drawn on the screen.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Kevin Kolyakov
 * @version 05.29.23
 */
public class Rectangle extends Actor {

    /** The texture of the rectangle. */
    private Texture texture;

    /**
     * Creates a rectangle with the given dimensions and color.
     * @param x The x coordinate of the rectangle.
     * @param y The y coordinate of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color The color of the rectangle.
     */
    public Rectangle(float x, float y, float width, float height, Color color) {
        // create the texture
        createTexture((int)width, (int)height, color);

        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    /**
     * creates the texture for the rectangle
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color The color of the rectangle.
     */
    private void createTexture(int width, int height, Color color) {
        // create a pixmap
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        // fill it with the color
        pixmap.setColor(color);

        // fill the rectangle
        pixmap.fillRectangle(0, 0, width, height);

        // create the texture
        texture = new Texture(pixmap);

        // dispose the pixmap
        pixmap.dispose();
    }

    /**
     * Draws the rectangle
     * @param batch The batch to draw the rectangle on.
     * @param parentAlpha The alpha of the parent.
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        // set the color
        Color color = getColor();

        // draw the rectangle
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Sets the transparency of the rectangle
     * @param alpha The transparency of the rectangle.
     */
    public void setTransparency(float alpha) {
        // set the color
        Color color = getColor();

        // set the alpha
        color.a = alpha;

        // set the color with updated alpha
        setColor(color);
    }
}