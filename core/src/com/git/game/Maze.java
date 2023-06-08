package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Maze implements Screen {

    private static final int MAZE_CELL_SIZE = Gdx.graphics.getWidth()/20; // Size of each maze cell in pixels
    private final Game game;
    private ShapeRenderer shapeRenderer;
    private final OrthographicCamera cam;
    private final float rotationSpeed;

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

    private Character character;
    public static float characterX = MAZE_CELL_SIZE;
    public static float characterY = MAZE_CELL_SIZE;
    private Vector2 characterVelocity;
    private final int numSnakes;

    public Maze(Game game, int NumSnakes) {
        this.game = game;
        numSnakes = NumSnakes;
        rotationSpeed = 0.5f;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(300, 300 * (h / w));

        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
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
        } else if (numSnakes == 2){
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
        shapeRenderer = new ShapeRenderer();

        mazeWidth = maze[0].length;
        mazeHeight = maze.length;

        character = new Character("character.png");
        // character.set(characterX, characterY, MAZE_CELL_SIZE/2, MAZE_CELL_SIZE/2);
        characterVelocity = new Vector2();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();
        updateCharacter(delta);

        cam.update();
        shapeRenderer.setProjectionMatrix(cam.combined);

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
        character.update(delta);
        character.render();
        // Render the character
        shapeRenderer.setColor(Color.GOLD);
        // shapeRenderer.rect(character.x, character.y, character.width, character.height);

        shapeRenderer.end();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cam.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            cam.rotate(rotationSpeed, 0, 0, 1);
        }

        cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100/cam.viewportWidth);

        float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
    }

    private void updateCharacter(float delta) {
//        float oldX = character.x;
//        float oldY = character.y;

        characterX += characterVelocity.x * delta;
        character.x = characterX;
        characterY += characterVelocity.y * delta;
        character.y = characterY;

        // Check for collision with maze walls
        int characterCol = (int) (character.x / MAZE_CELL_SIZE);
        int characterRow = (int) (character.y / MAZE_CELL_SIZE);

        Rectangle characterRect = new Rectangle(character.x+15, character.y+10, 25, 50);

        for (int row = characterRow; row <= characterRow + 1; row++) {
            for (int col = characterCol; col <= characterCol + 1; col++) {
                Rectangle wallRect = new Rectangle(col * MAZE_CELL_SIZE, row * MAZE_CELL_SIZE, MAZE_CELL_SIZE, MAZE_CELL_SIZE);
                if (maze[row][col] == 1) {
                    if (characterRect.overlaps(wallRect)) {
                        // Collision with wall or top wall, move character back to previous position
                        characterX = oldX;
                        characterY = oldY;
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
                    game.setScreen(new TransitionAnimation(game, new FinalLevel(game), "You have Completed the maze!\nContinue to your final challenge."));
                }
            }
        }
//        character.x += characterVelocity.x * delta;
//        characterX = character.x;
//        character.y += characterVelocity.y * delta;
//        characterY = character.y;

        // Check for collision with maze walls
//        int characterCol = (int) (character.x / MAZE_CELL_SIZE);
//        int characterRow = (int) (character.y / MAZE_CELL_SIZE);

//        Rectangle characterRect = new Rectangle(character.x, character.y, 64, 64);
//
//        for (int row = characterRow; row <= characterRow + 1; row++) {
//            for (int col = characterCol; col <= characterCol + 1; col++) {
//                Rectangle wallRect = new Rectangle(col * MAZE_CELL_SIZE, row * MAZE_CELL_SIZE, MAZE_CELL_SIZE, MAZE_CELL_SIZE);
//                if (maze[row][col] == 1) {
//                    if (characterRect.overlaps(wallRect)) {
//                        // Collision with wall or top wall, move character back to previous position
//                        character.x = oldX;
//                        character.y = oldY;
//                    }
//                } else if (maze[row][col] == 2) {
//
//                    if (numSnakes == 3 && characterRect.overlaps(wallRect)){
//                        game.setScreen(new Popup(game, new Snake1(game), "You have encountered a snake!\nComplete the snake's challenge to continue."));
//                    } else if (numSnakes == 2 && characterRect.overlaps(wallRect)){
//                        game.setScreen(new Popup(game, new Snake2(game), "You have encountered a snake!\nComplete the snake's challenge to continue."));
//                    } else if (numSnakes == 1 && characterRect.overlaps(wallRect)){
//                        game.setScreen(new Popup(game, new Snake3(game), "You have encountered a snake!\nComplete the snake's challenge to continue."));
//                    }
//                } else if (maze[row][col] == 3 && characterRect.overlaps(wallRect)) {
//                    game.setScreen(new Popup(game, new FinalLevel(), "You have Completed the maze!\nContinue to your final challenge."));
//                }
//            }
//        }
    }



    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = 30f;
        cam.viewportHeight = 30f * height/width;
        cam.update();

        cam.viewportWidth = width/32f;
        cam.viewportHeight = cam.viewportWidth * height/width;
        cam.update();
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
