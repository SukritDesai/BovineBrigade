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
 * This is the third challenge in the maze game for the third snake
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Sukrit Desai
 * @version 06.05.23
 * Time Spent: 30 minutes
 */
public class Snake3 implements Screen {

    /** The width of the screen */
    private static final int SCREEN_WIDTH = 800;

    /** The height of the screen */
    private static final int SCREEN_HEIGHT = 480;

    /** The stage that holds the actors */
    private Stage stage;

    /** The viewport that controls the camera */
    private Viewport viewport;

    /** The sprite batch that draws the actors */
    private SpriteBatch spriteBatch;

    /** The shape renderer that draws the shapes */
    private ShapeRenderer shapeRenderer;

    /** The text area that holds the console output */
    private TextField consoleTextField;

    /** The lavels that holds the information on screen */
    Label output, firstLabel, secondLabel, thirdLabel, fourthLabel, fifthLabel, instructionsLabel;

    /** The rectangles that represent the nodes */
    Rectangle first, second, third, fourth, fifth, connector1, connector2, connector3, connector4, connector5, outline;


    /** The counter that keeps track of the number of times the user has entered the right command */
    private int counter = 0;

    /** The game object */
    Game game;

    /**
     * This is the constructor for the Snake3 (the third challenge) class.
     * @param aGame The game object
     */
    public Snake3(Game aGame){
        game = aGame;
    }

    /**
     * This method is called when the screen is first created.
     * It creates the rectangles and labels and adds them to the stage.
     */
    @Override
    public void show() {
        gameSkin.getFont("commodore-64").getData().setScale(0.8f);
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        // Create and position the rectangles in the left half of the screen
        float rectangleWidth = SCREEN_WIDTH / 8f;
        float rectangleX = 1f;
        float rectangleY = 330;

        // Create and position the labels in the left half of the screen
        Label title = new Label("Visual Representation", gameSkin, "default");
        firstLabel = new Label("", gameSkin, "default");
        secondLabel = new Label("", gameSkin, "default");
        thirdLabel = new Label("", gameSkin, "default");
        fourthLabel = new Label("", gameSkin, "default");
        fifthLabel = new Label("", gameSkin, "default");

        // Set the position of the labels
        title.setPosition(10, 445);
        firstLabel.setPosition(30, 380);
        secondLabel.setPosition(30, 255);
        thirdLabel.setPosition(180, 255);
        fourthLabel.setPosition(180, 130);
        fifthLabel.setPosition(30, 90);

        // Create and position the rectangles
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

        // Set the transparency of the rectangles
        connector2.setTransparency(0f);
        connector3.setTransparency(0f);
        connector4.setTransparency(0f);
        connector5.setTransparency(0f);
        third.setTransparency(0f);
        fourth.setTransparency(0f);
        fifth.setTransparency(0f);

        // Set the text of the second label
        secondLabel.setText("035cc");

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
        instructionsLabel = new Label("\n\nInstructions:\nThe last commit made by a user was \nfaulty. Reset the commit to 9e78i.", gameSkin, "default");
        instructionsLabel.setPosition(SCREEN_WIDTH / 2f, SCREEN_HEIGHT - instructionsLabel.getHeight()+20);
        instructionsLabel.setFontScale(0.6f);

        // Create and position the console text area in the bottom right
        float consoleWidth = SCREEN_WIDTH / 2f;
        float consoleX = SCREEN_WIDTH / 2f;
        consoleTextField = new TextArea("", gameSkin, "default");
        ScrollPane scrollPane  = new ScrollPane(consoleTextField);
        scrollPane.setPosition(consoleX+20, 130);
        scrollPane.setScrollingDisabled(true, false);
        consoleTextField.setSize(consoleWidth+80, 310);
        consoleTextField.setPosition(consoleX+10, 20);
        output = new Label("", gameSkin, "default");
        output.setPosition(consoleX+20, 80);
        output.setSize(20, 20);
        output.setColor(Color.BLACK);

        // Add elements to the stage
        stage.addActor(instructionsLabel);
        stage.addActor(consoleTextField);
        stage.addActor(output);
    }

    /**
     * This method is called when the screen should render itself.
     * It renders the stage and checks if the user has entered the correct command for the final challenge
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        // Set the background color
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Set the transparency of the first rectangle to be seen
        first.setTransparency(1f);

        // Set the text of the first label
        firstLabel.setText("9e78i");

        // The stage acts and is drawn
        stage.act(delta);
        stage.draw();

        // Check if the user presses enter in the console text field
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) { // If the user presses enter
            String errorMessage = "Make sure to read the\ninstructions of the level\ntype in the git reset\ncommand correctly.";
            if (consoleTextField.getText().split("\n").length<1){// If the user has not entered anything errortraps
                output.setColor(Color.RED);
                output.setText("Error:\n"+ errorMessage);
            } else if (counter == 0 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git reset 9e78i")) {// If the user has entered the correct command
                output.setColor(Color.GREEN);
                output.setText("Correct");
                secondLabel.setText("");
                connector1.setTransparency(0f);
                second.setTransparency(0f);
                instructionsLabel.setText("\n\nYou have completed the first challenge!\nPress enter to continue");
                counter++;

            } else if (counter == 1) {// If the user has pressed enter after completing the challenge sets screen back to maze
                game.setScreen(new Maze(game, 0));
            } else {// If the user has entered the wrong command
                output.setColor(Color.RED);
                output.setText("Error:\n"+ errorMessage);
            }
        }
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

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
