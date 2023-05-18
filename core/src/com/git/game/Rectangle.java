package com.git.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Rectangle extends Actor {
    private Texture texture;

    public Rectangle(float x, float y, float width, float height, com.badlogic.gdx.graphics.Color color) {
        createTexture((int)width, (int)height, color);
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    private void createTexture(int width, int height, com.badlogic.gdx.graphics.Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        com.badlogic.gdx.graphics.Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}