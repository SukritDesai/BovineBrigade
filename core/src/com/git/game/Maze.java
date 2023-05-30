package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Maze implements Screen {

    private static final int MAZE_CELL_SIZE = Gdx.graphics.getWidth()/20; // Size of each maze cell in pixels
    private static final float CHARACTER_SPEED = 100f; // Character movement speed

    private Game game;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 2, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 2, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 0, 1, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1}
    };
    private int mazeWidth;
    private int mazeHeight;

    private Rectangle character;
    public static float characterX = MAZE_CELL_SIZE;
    public static float characterY = MAZE_CELL_SIZE;
    private Vector2 characterVelocity;
    private final int numSnakes;

    public Maze(Game game, int NumSnakes) {
        this.game = game;
        numSnakes = NumSnakes;
    }

    @Override
    public void show() {
        if (numSnakes==3){
            maze = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1},
                    {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
                    {1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1},
                    {1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 2, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 2, 1, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 0, 1, 1, 1, 0, 1, 1},
                    {1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1}
            };
        } else if (numSnakes== 2){
            maze = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1},
                    {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
                    {1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1},
                    {1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 2, 1, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 0, 1, 1, 1, 0, 1, 1},
                    {1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1}
            };
        } else if (numSnakes == 1) {
            maze = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1},
                    {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
                    {1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1},
                    {1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 2, 1, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1},
                    {1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1}
            };
        } else if (numSnakes == 0) {
            maze = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1},
                    {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
                    {1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1},
                    {1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1},
                    {1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1}
            };
        }
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        shapeRenderer = new ShapeRenderer();

        mazeWidth = maze[0].length;
        mazeHeight = maze.length;

        character = new Rectangle();
        character.set(characterX, characterY, MAZE_CELL_SIZE/2, MAZE_CELL_SIZE/2);
        characterVelocity = new Vector2();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();
        updateCharacter(delta);

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Render the maze walls
        for (int row = 0; row < mazeHeight; row++) {
            for (int col = 0; col < mazeWidth; col++) {
                if (maze[row][col] == 1) {
                    shapeRenderer.setColor(Color.WHITE);
                    shapeRenderer.rect(col * MAZE_CELL_SIZE, row * MAZE_CELL_SIZE,
                            MAZE_CELL_SIZE, MAZE_CELL_SIZE);
                }
                else if (maze[row][col] == 2) {
                    shapeRenderer.setColor(Color.GREEN);
                    shapeRenderer.rect(col * MAZE_CELL_SIZE, row * MAZE_CELL_SIZE,
                            MAZE_CELL_SIZE, MAZE_CELL_SIZE);
                }
                else if (maze[row][col] == 3) {
                    shapeRenderer.setColor(Color.RED);
                    shapeRenderer.rect(col * MAZE_CELL_SIZE, row * MAZE_CELL_SIZE,
                            MAZE_CELL_SIZE, MAZE_CELL_SIZE);
                }
            }
        }

        // Render the character
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.rect(character.x, character.y, character.width, character.height);

        shapeRenderer.end();
    }

    private void handleInput() {
        characterVelocity.set(0, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            characterVelocity.x = -CHARACTER_SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            characterVelocity.x = CHARACTER_SPEED;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            characterVelocity.y = CHARACTER_SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            characterVelocity.y = -CHARACTER_SPEED;
        }
    }

    private void updateCharacter(float delta) {
        float oldX = character.x;
        float oldY = character.y;

        character.x += characterVelocity.x * delta;
        characterX = character.x;
        character.y += characterVelocity.y * delta;
        characterY = character.y;

        // Check for collision with maze walls
        int characterCol = (int) (character.x / MAZE_CELL_SIZE);
        int characterRow = (int) (character.y / MAZE_CELL_SIZE);

        Rectangle characterRect = new Rectangle(character.x, character.y, character.width, character.height);

        for (int row = characterRow; row <= characterRow + 1; row++) {
            for (int col = characterCol; col <= characterCol + 1; col++) {
                Rectangle wallRect = new Rectangle(col * MAZE_CELL_SIZE, row * MAZE_CELL_SIZE,
                        MAZE_CELL_SIZE, MAZE_CELL_SIZE);
                if (maze[row][col] == 1) {
                    if (characterRect.overlaps(wallRect)) {
                        // Collision with wall or top wall, move character back to previous position
                        character.x = oldX;
                        character.y = oldY;
                    }
                } else if (maze[row][col] == 2) {

                    if (numSnakes == 3 && characterRect.overlaps(wallRect)){
                        game.setScreen(new Popup(game, new Snake1(game), "You have encountered a snake!\nComplete the snake's challenge to continue."));
                    } else if (numSnakes == 2 && characterRect.overlaps(wallRect)){
                        game.setScreen(new Popup(game, new Snake2(game), "You have encountered a snake!\nComplete the snake's challenge to continue."));
                    } else if (numSnakes == 1 && characterRect.overlaps(wallRect)){
                        game.setScreen(new Popup(game, new Snake3(game), "You have encountered a snake!\nComplete the snake's challenge to continue."));
                    }
                } else if (maze[row][col] == 3 && characterRect.overlaps(wallRect)) {
                    game.setScreen(new Popup(game, new MainMenu(game), "You have Completed the maze!\n Continue to your final challenge."));
                }
            }
        }
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
        shapeRenderer.dispose();
    }
}
