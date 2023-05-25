/**
 * Name: Kevin Kolyakov
 * Teacher: Ms. Krasteva
 * The ComputerScreen class represents a screen that displays a computer interface.
 */

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

public class Snake2 implements Screen {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    private Stage stage;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private TextField consoleTextField;
    Label output, firstLabel, secondLabel, thirdLabel, fourthLabel, fifthLabel, instructionsLabel;
    Rectangle first, second, third, fourth, fifth, connector1, connector2, connector3, connector4, connector5, outline;
    private int counter = 0;
    Game game;
    public Snake2(Game aGame){
        game = aGame;
    }

    @Override
    public void show() {
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
        Label title = new Label("Visual Representation", gameSkin, "default");
        firstLabel = new Label("", gameSkin, "default");
        secondLabel = new Label("", gameSkin, "default");
        thirdLabel = new Label("", gameSkin, "default");
        fourthLabel = new Label("", gameSkin, "default");
        fifthLabel = new Label("", gameSkin, "default");
        title.setPosition(10, 445);
        firstLabel.setPosition(30, 380);
        secondLabel.setPosition(30, 255);
        thirdLabel.setPosition(180, 255);
        fourthLabel.setPosition(180, 130);
        fifthLabel.setPosition(30, 90);
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
        instructionsLabel = new Label("Instructions:\nWelcome to the Git Odyssey. The window \nbelow this one is your command line. Enter \nthe git commands that I explain in that \nbox. The window to your left contains \nthe visualization for that command\nUse git init in the command line to\ninitialize the repository in Git", gameSkin, "default");
        instructionsLabel.setPosition(SCREEN_WIDTH / 2f, SCREEN_HEIGHT - instructionsLabel.getHeight()+20);
        instructionsLabel.setFontScale(0.6f);

        // Create and position the console text area in the bottom right
        float consoleWidth = SCREEN_WIDTH / 2f;
        float consoleX = SCREEN_WIDTH / 2f;
        consoleTextField = new TextArea("", gameSkin, "default");
        ScrollPane scrollPane  = new ScrollPane(consoleTextField);
        scrollPane.setPosition(consoleX+20, 100);
        scrollPane.setScrollingDisabled(true, false);
        consoleTextField.setSize(consoleWidth+80, 350);
        consoleTextField.setPosition(consoleX+10, 0);
        output = new Label("", gameSkin, "default");
        output.setPosition(consoleX+20, 50);
        output.setSize(20, 20);
        output.setColor(Color.BLACK);

        // Add elements to the stage
        stage.addActor(instructionsLabel);
        stage.addActor(consoleTextField);
        stage.addActor(output);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        // Check if the user presses enter in the console text field
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (counter == 0 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git init")) {
                instructionsLabel.setText("Instructions:\nnew thing");
                output.setColor(Color.GREEN);
                output.setText("This is to be completed");
                first.setTransparency(1f);
                firstLabel.setText("9e78i");
                counter++;

            } else if (counter == 1 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git clone")) {
                instructionsLabel.setText("Instructions:\nnew instructions");
                output.setColor(Color.GREEN);
                output.setText("This is to be completed");
                counter++;

            }else if (counter == 2 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git add")) {
                instructionsLabel.setText("Instructions:\nplaceholder");
                output.setColor(Color.GREEN);
                output.setText("This is to be completed");
                counter++;

            }else if (counter == 3 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git commit -m \"message\"")) {
                instructionsLabel.setText("Instructions:\nidk man");
                output.setColor(Color.GREEN);
                output.setText("This is to be completed");
                counter++;

            }else if (counter == 4 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git push")) {
                instructionsLabel.setText("Instructions:\nbruh");
                output.setColor(Color.GREEN);
                output.setText("This is to be completed");
                second.setTransparency(1f);
                connector1.setTransparency(1f);
                secondLabel.setText("035cc");
                counter++;

            } else if (counter == 5 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git branch \"dev\"")) {
                third.setTransparency(1f);
                thirdLabel.setText("e3475");
                connector3.setTransparency(1f);
                counter++;
            }else if (counter == 6 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git pull")) {
                instructionsLabel.setText("Instructions:\nnew thing");
                output.setColor(Color.GREEN);
                output.setText("This is to be completed");
                counter++;

            }else if (counter == 7 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git add README.md")) {
                instructionsLabel.setText("Instructions:\nnew thing");
                output.setColor(Color.GREEN);
                output.setText("This is to be completed");
                counter++;

            }else if (counter == 8 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git commit -m \"README.md\"")) {
                fourthLabel.setText("76d12");
                fifth.setTransparency(1f);
                connector4.setTransparency(1f);
                counter++;

            }else if (counter == 9 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git merge dev master")) {
                fourth.setTransparency(1f);
                connector2.setTransparency(1f);
                connector5.setTransparency(1f);
                fifthLabel.setText("i8fe5");
                counter++;

            }else if (counter == 10 && consoleTextField.getText().split("\n")[consoleTextField.getText().split("\n").length-1].trim().equals("git revert 76d12")) {
                fourth.setTransparency(0f);
                connector2.setTransparency(0f);
                connector5.setTransparency(0f);
                fifthLabel.setText("");
                counter++;
            } else {
                output.setColor(Color.RED);
                output.setText("Error, please enter the\ncorrect command");
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
