package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.git.game.GitOdyssey.gameSkin;

/**
 * This is the first challenge in the maze for the first snake
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Kevin Kolyakov
 * @version 06.05.23
 */
public class Snake1 implements Screen {

    /** The width of the screen */
    private static final int SCREEN_WIDTH = 800;

    /** The height of the screen */
    private static final int SCREEN_HEIGHT = 480;

    /** The text that is displayed when the user enters the wrong command */
    private String errorMessage = "Make sure to read the\ninstructions of the level\ntype in the \ngit add command correctly.";

    /** the sage that holds all the actors */
    private Stage stage;

    /** the viewport that holds the stage */
    private Viewport viewport;

    /** the sprite batch that draws the stage */
    private SpriteBatch spriteBatch;

    /** the shape renderer that draws the shapes */
    private ShapeRenderer shapeRenderer;

    /** the text area that holds the console */
    private TextField consoleTextField;

    /** the labels for all the different parts of the level */
    Label output, firstLabel, secondLabel, thirdLabel, fourthLabel, fifthLabel, instructionsLabel;

    /** the rectangles that represent the different parts of the level */
    Rectangle first, second, third, fourth, fifth, connector1, connector2, connector3, connector4, connector5, outline;

    /** the string that holds the number of times the user has gotten a correct input */
    private int counter = 0;
    Game game;

    /**
     * This is the constructor for the class Snake1
     * @param aGame the game that the screen is in
     */
    public Snake1(Game aGame){
        game = aGame;
    }

    /**
     * This method is called when the screen is first created.
     * It creates the rectangles and labels and adds them to the stage.
     */
    @Override
    public void show() {
        // Set up the stage and the sprites
        gameSkin.getFont("commodore-64").getData().setScale(0.8f);
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        // Create the stage and make it take input
        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        // Creates the rectangles and labels for the different parts of the level
        float rectangleWidth = SCREEN_WIDTH / 8f;
        float rectangleX = 1f;
        float rectangleY = 330;
        Label title = new Label("Visual Representation", gameSkin, "default");
        firstLabel = new Label("", gameSkin, "default");
        secondLabel = new Label("", gameSkin, "default");
        thirdLabel = new Label("", gameSkin, "default");
        fourthLabel = new Label("", gameSkin, "default");
        fifthLabel = new Label("", gameSkin, "default");

        //sets the positions of the labels
        title.setPosition(10, 445);
        firstLabel.setPosition(30, 380);
        secondLabel.setPosition(30, 255);
        thirdLabel.setPosition(180, 255);
        fourthLabel.setPosition(180, 130);
        fifthLabel.setPosition(30, 90);

        //sets the rectangle positions
        outline = new Rectangle(rectangleX-60, rectangleY-300, rectangleWidth+310, rectangleWidth+340, Color.BLACK);
        connector1 = new Rectangle(rectangleX+65, rectangleY-50, 10, 50, Color.WHITE);
        connector2 = new Rectangle(rectangleX+65, rectangleY-200, 10, 100, Color.WHITE);
        connector3 = new Rectangle(rectangleX+110, rectangleY-80, 60, 10, Color.WHITE);
        connector4 = new Rectangle(rectangleX+215, rectangleY-175, 10, 50, Color.WHITE);
        connector5 = new Rectangle(rectangleX+120, rectangleY-230, 100, 10, Color.WHITE);
        first = new Rectangle(rectangleX+20, rectangleY, rectangleWidth, rectangleWidth, Color.RED);
        second = new Rectangle(rectangleX+20, rectangleY-125, rectangleWidth, rectangleWidth, Color.RED);
        third = new Rectangle(rectangleX+170, rectangleY-125, rectangleWidth, rectangleWidth, Color.YELLOW);
        fifth = new Rectangle(rectangleX+170, rectangleY-250, rectangleWidth, rectangleWidth, Color.YELLOW);
        fourth = new Rectangle(rectangleX+20, rectangleY-290, rectangleWidth, rectangleWidth, Color.RED);

        //sets the transparency of the rectangles
        connector1.setTransparency(0f);
        connector2.setTransparency(0f);
        connector3.setTransparency(0f);
        connector4.setTransparency(0f);
        connector5.setTransparency(0f);
        first.setTransparency(0f);
        second.setTransparency(0f);
        third.setTransparency(0f);
        fourth.setTransparency(0f);
        fifth.setTransparency(0f);

        // Add the actors to the stage
        stage.addActor(outline);
        stage.addActor(connector1);
        stage.addActor(connector2);
        stage.addActor(connector3);
        stage.addActor(connector4);
        stage.addActor(connector5);
        stage.addActor(first);
        stage.addActor(second);
        stage.addActor(third);
        stage.addActor(fourth);
        stage.addActor(fifth);
        stage.addActor(title);
        stage.addActor(firstLabel);
        stage.addActor(secondLabel);
        stage.addActor(thirdLabel);
        stage.addActor(fourthLabel);
        stage.addActor(fifthLabel);

        // Create and position the instructions label in the top right
        instructionsLabel = new Label("\n\nInstructions:\nadd, commit and push the README.md \nfile with the message \"first\" to the\ncurrent online repository", gameSkin, "default");
        instructionsLabel.setPosition(SCREEN_WIDTH / 2f, SCREEN_HEIGHT - instructionsLabel.getHeight()+20);
        instructionsLabel.setFontScale(0.6f);

        // Create and position the console text area in the bottom right
        float consoleWidth = SCREEN_WIDTH / 2f;
        float consoleX = SCREEN_WIDTH / 2f;
        consoleTextField = new TextArea("", gameSkin, "default");
        ScrollPane scrollPane  = new ScrollPane(consoleTextField);
        scrollPane.setPosition(consoleX+20, 100);
        scrollPane.setScrollingDisabled(true, false);
        consoleTextField.setSize(consoleWidth+80, 310);
        consoleTextField.setPosition(consoleX+10, 20);
        output = new Label("", gameSkin, "default");
        output.setPosition(consoleX+20, 100);
        output.setSize(20, 20);
        output.setColor(Color.BLACK);

        // Add elements to the stage
        stage.addActor(instructionsLabel);
        stage.addActor(consoleTextField);
        stage.addActor(output);
    }

    /**
     * This method is called when the screen should render itself.
     * It renders the stage and checks if the user has entered the correct command for the first challenge
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        first.setTransparency(1f);
        firstLabel.setText("9e78i");
        stage.act(delta);
        stage.draw();

        // Check if the user presses enter in the console text field
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (consoleTextField.getText().split("\n").length<1){//if the user has not entered anything
                output.setColor(Color.RED);
                output.setText("Error\nMake sure to read the instructions of the level\ntype in the git add\ncommand correctly");
            }
            else if (counter == 0 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git add README.md")) {//if the user has entered the correct command
                output.setColor(Color.GREEN);
                errorMessage = "Make sure to read the\ninstructions of the level\ntype in the git commit\ncommand correctly";
                output.setText("Correct!");
                counter++;

            } else if (counter == 1 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git commit -m \"first\"")) {//if the user has entered the correct command
                output.setColor(Color.GREEN);
                errorMessage = "Make sure to read the\ninstructions of the level\ntype in the git push\ncommand correctly";
                output.setText("Well done!");
                counter++;

            }else if (counter == 2 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git push")) {//if the user has entered the correct command
                output.setColor(Color.GREEN);
                output.setText("Correct!");
                second.setTransparency(1f);
                connector1.setTransparency(1f);
                secondLabel.setText("035cc");
                instructionsLabel.setText("\n\nYou have completed the first challenge!\nPress enter to continue");
                counter++;

            }else if (counter == 3) {//if the user has pressed enter after completing the challenge
                game.setScreen(new Maze(game, 2));

            } else {//if the user has entered the wrong command
                output.setColor(Color.RED);
                output.setText("Error:\n"+errorMessage);
            }
        }
    }


    @Override
    public void resize(int width, int height) {viewport.update(width, height);}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
        stage.dispose();
    }
}
