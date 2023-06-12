package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * The maze game screen. The player must navigate through the maze to reach the end.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Kevin Kolyakov, Sukrit Desai
 * @version 05.23.23
 * Time Spent: 5 hours
 */
public class Maze implements Screen {

    /** The size of each maze cell in pixels */
    private static final int MAZE_CELL_SIZE = Gdx.graphics.getWidth()/20;

    /** the character movement speed */
    private static final float CHARACTER_SPEED = 100f;

    /** The game object used to switch between screens */
    private final Game game;

    /** The camera object to render everything properly */
    private OrthographicCamera camera;

    /** The shape renderer object to draw shapes */
    private ShapeRenderer shapeRenderer;

    /** The sprite batch that is used to render the images */
    private final SpriteBatch spriteBatch = new SpriteBatch();

    /** The texture that is used to render the snakes */
    private final Texture snakeTexture = new Texture("Snake.png");

    /** The texture that is used to render the exit */
    private final Texture exitTexture = new Texture("Exit.png");

    /** definition of the maze layout */
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

    /** The width of the maze in cells */
    private int mazeWidth;

    /** The height of the maze in cells */
    private int mazeHeight;

    /** the character object used to draw the character */
    private Character character;

    /** the x position of the character */
    public static float characterX = MAZE_CELL_SIZE;

    /** the y position of the character */
    public static float characterY = MAZE_CELL_SIZE;

    /** the velocity of the character used to set new character positions */
    private Vector2 characterVelocity;

    /** the number of snakes in the maze */
    private final int numSnakes;


    /**
     * The constructor for the maze screen.
     * @param game The game object used to switch between screens
     * @param NumSnakes The number of snakes in the maze
     */
    public Maze(Game game, int NumSnakes) {
        this.game = game;
        numSnakes = NumSnakes;
    }

    /**
     * The method that is called when the screen is first loaded.
     */
    @Override
    public void show() {
        if (numSnakes==3){ // layout for if there's 3 snakes
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
        } else if (numSnakes == 2){ // changed layout for 2 snakes
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
        } else if (numSnakes == 1) { // changed layout for 1 snake
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
        } else if (numSnakes == 0) { // changed layout for no snakes
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

        // initializes basic variables
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        shapeRenderer = new ShapeRenderer();

        mazeWidth = maze[0].length;
        mazeHeight = maze.length;

        //Used https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?body=Body_color_light&head=Human_male_light to create character
        character = new Character("character.png");
        characterVelocity = new Vector2();
    }

    /**
     * Renders the maze and the character, and also called the handleInput() function.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();
        updateCharacter(delta);

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        character.update(delta);
        character.render();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        spriteBatch.begin();

        // Render the maze walls
        for (int row = 0; row < mazeHeight; row++) {
            for (int col = 0; col < mazeWidth; col++) {
                if (maze[row][col] == 1) {
                    shapeRenderer.setColor(Color.WHITE);
                    shapeRenderer.rect(col * MAZE_CELL_SIZE, row * MAZE_CELL_SIZE,
                            MAZE_CELL_SIZE, MAZE_CELL_SIZE);
                }
                else if (maze[row][col] == 2) {
                    if (numSnakes == 1)
                        spriteBatch.draw(snakeTexture, 1091, 384);
                    else if (numSnakes == 2) {
                        spriteBatch.draw(snakeTexture, 1091, 384);
                        spriteBatch.draw(snakeTexture, 636, 513);
                    }
                    else if (numSnakes == 3) {
                        spriteBatch.draw(snakeTexture, 247, 387);
                        spriteBatch.draw(snakeTexture, 636, 513);
                        spriteBatch.draw(snakeTexture, 1091, 384);
                    }

                }
                else if (maze[row][col] == 3) {
                    spriteBatch.draw(exitTexture, 1110, 620);
                }
            }
        }
        spriteBatch.end();
        // Render the character
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.end();
    }

    /**
     * Handles user input by checking for keys pressed and moves the character accordingly on the screen.
     */
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

    /**
     * Updates the character's position and checks for collision with maze walls tiles.
     */
    private void updateCharacter(float delta) {
        float oldX = character.x;
        float oldY = character.y;

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

                    // Collision with snake will trigger a popup screen
                    if (numSnakes == 3 && characterRect.overlaps(wallRect)){
                        game.setScreen(new Popup(game, new Snake1(game), "You have encountered a snake!\nComplete the snake's challenge to continue."));
                    } else if (numSnakes == 2 && characterRect.overlaps(wallRect)){
                        game.setScreen(new Popup(game, new Snake2(game), "You have encountered a snake!\nComplete the snake's challenge to continue."));
                    } else if (numSnakes == 1 && characterRect.overlaps(wallRect)){
                        game.setScreen(new Popup(game, new Snake3(game), "You have encountered a snake!\nComplete the snake's challenge to continue."));
                    }
                } else if (maze[row][col] == 3 && characterRect.overlaps(wallRect))  {// Collision with the end of the maze will trigger a popup screen to next screen
                    game.setScreen(new TransitionAnimation(game, new FinalLevel(game), "Congratulations on conquering the intricate maze! But hold on tight,\nfor your final challenge awaits. Keep your\nwits sharp as you guide your character\nthrough this ultimate test of skill. Utilize the arrow keys to\nswiftly navigate the obstacles and remember, speed is of the essence!"));
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

    /**
     * Disposes of the shapeRenderer.
     */
    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
