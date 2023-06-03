/**
 * Name: Kevin Kolyakov, Sukrit Desai
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

import java.util.Arrays;

import static com.git.game.GitOdyssey.gameSkin;

public class LearningLevel implements Screen {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    private Stage stage;
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private TextField consoleTextField;
    Label output, firstLabel, secondLabel, thirdLabel, fourthLabel, fifthLabel, instructionsLabel;
    Rectangle first, second, third, fourth, fifth, connector1, connector2, connector3, connector4, connector5, outline;
    private int counter = 1;
    Game game;
    public LearningLevel(Game aGame){
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
        float rectangleSize = SCREEN_WIDTH / 8f;
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
        outline = new Rectangle(rectangleX - 60, rectangleY - 300, rectangleSize + 310, rectangleSize+340, Color.BLACK);
        connector1 = new Rectangle(rectangleX + 65, rectangleY - 50, 10, 50, Color.WHITE);
        connector2 = new Rectangle(rectangleX+65, rectangleY-200, 10, 100, Color.WHITE);
        connector3 = new Rectangle(rectangleX+110, rectangleY-80, 60, 10, Color.WHITE);
        connector4 = new Rectangle(rectangleX+215, rectangleY-175, 10, 50, Color.WHITE);
        connector5 = new Rectangle(rectangleX+120, rectangleY-230, 100, 10, Color.WHITE);
        first = new Rectangle(rectangleX+20, rectangleY, rectangleSize, rectangleSize, Color.RED);
        second = new Rectangle(rectangleX+20, rectangleY-125, rectangleSize, rectangleSize, Color.RED);
        third = new Rectangle(rectangleX+170, rectangleY-125, rectangleSize, rectangleSize, Color.YELLOW);
        fifth = new Rectangle(rectangleX+170, rectangleY-250, rectangleSize, rectangleSize, Color.YELLOW);
        fourth = new Rectangle(rectangleX+20, rectangleY-290, rectangleSize, rectangleSize, Color.RED);
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

        for (Rectangle rectangle : Arrays.asList(third, fourth, fifth)) {
            stage.addActor(rectangle);
        }
        stage.addActor(title);
        stage.addActor(firstLabel);
        stage.addActor(secondLabel);
        stage.addActor(thirdLabel);
        stage.addActor(fourthLabel);

        stage.addActor(fifthLabel);

        // Create and position the instructions label in the top right
        instructionsLabel = new Label("Instructions:\nWelcome to the Git Odyssey. The window \nbelow this one is your command line. Enter \nthe git commands that I explain in that \nbox. The window to your left contains \nthe visualization for that command\nUse git init in the command line to\ninitialize the repository in Git\nPress enter in the console to continue", gameSkin, "default");
        instructionsLabel.setPosition(SCREEN_WIDTH / 2f, SCREEN_HEIGHT - instructionsLabel.getHeight()+20);
        instructionsLabel.setFontScale(0.5f);

        // Create and position the console text area in the bottom right
        float consoleWidth = SCREEN_WIDTH / 2f;
        float consoleX = SCREEN_WIDTH / 2f;
        consoleTextField = new TextArea("", gameSkin, "default");
        gameSkin.getFont("commodore-64").getData().setScale(0.5f);
        ScrollPane scrollPane  = new ScrollPane(consoleTextField);
        scrollPane.setPosition(consoleX+20, 100);
        scrollPane.setScrollingDisabled(true, false);
        consoleTextField.setSize(consoleWidth+80, 350);
        consoleTextField.setPosition(consoleX+10, 0);
        Rectangle extra = new Rectangle(consoleX+10, 7, consoleWidth+80, 350, Color.WHITE);
        output = new Label("", gameSkin, "default");
        output.setPosition(consoleX+20, 50);
        output.setSize(20, 20);
        output.setColor(Color.BLACK);

        // Add elements to the stage
        stage.addActor(extra);
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
            if (counter==1) {
                instructionsLabel.setText("The first command relating to git that one\nmust learn is git clone which is used to\nclone a repository into your local machine.\nThe command is used as follows:\ngit clone <url>.The url is the url of the\nrepository that you want to clone.\nIn this case, the url is https://odyssey.\ngit. \nThe command is used as follows:\ngit clone https://odyssey.git");
            }
            if (consoleTextField.getText().split("\n").length<1){
                output.setColor(Color.RED);
                output.setText("");

            } else if (consoleTextField.getText().split("\n").length > 0) {
                String[] consoleText = consoleTextField.getText().split("\n");
                String trimmed = consoleText[consoleText.length - 1].trim();
                if (counter == 1 && trimmed.equals("git clone https://odyssey.git")) {
                    instructionsLabel.setText("Instructions:\nType in \"git add README.md\" in the command line\nThis will add the file to commit.");
                    first.setTransparency(1f);
                    firstLabel.setText("9e78i");
                    output.setColor(Color.GREEN);
                    output.setText("Cloning into 'odyssey'...\nremote: Enumerating objects: 3, done.\nremote: Counting objects: 100% (3/3), done.\nremote: Total 3 (delta 0), reused 0 (delta 0)\nUnpacking objects: 100% (3/3), done.");
                    counter++;
                } else if (counter == 2 && trimmed.equals("git add README.md")) {
                    instructionsLabel.setText("Instructions:\nType in \"git commit -m \"message\"\" in the \ncommand line. This will commit the files to your\nlocal repository.");
                    output.setColor(Color.GREEN);
                    output.setText("Well Done!");
                    counter++;
                } else if (counter == 3 && trimmed.equals("git commit -m \"message\"")) {
                    instructionsLabel.setText("Instructions:\nType in \"git push\" in the command line\nThis will add the files to the online repository.");
                    output.setColor(Color.GREEN);
                    output.setText("Correct!");
                    counter++;
                } else if (counter == 4 && trimmed.equals("git push")) {
                    instructionsLabel.setText("Instructions:\nType in \"git branch \"dev\"\" in the command line\nThis will create a branch of the repository\nwhere you can make changes to\na different version than the master.");
                    output.setColor(Color.GREEN);
                    output.setText("Well Done!");
                    second.setTransparency(1f);
                    connector1.setTransparency(1f);
                    secondLabel.setText("035cc");
                    counter++;
                } else if (counter == 5 && trimmed.equals("git branch \"dev\"")) {
                    instructionsLabel.setText("Instructions:\nType in \"git checkout dev\" in the command line\n This will switch the branch you are editing.");
                    third.setTransparency(1f);
                    thirdLabel.setText("e3475");
                    connector3.setTransparency(1f);
                    output.setColor(Color.GREEN);
                    output.setText("Correct!");
                    counter++;
                } else if (counter == 6 && trimmed.equals("git checkout dev")) {
                    instructionsLabel.setText("Instructions:\nType in \"git add README1.md\" in the command line\n This will add a file to the new branch.");
                    output.setColor(Color.GREEN);
                    output.setText("Well Done!");
                    counter++;
                } else if (counter == 7 && trimmed.equals("git add README1.md")) {
                    instructionsLabel.setText("Instructions:\nType in \"git commit -m \"README.md\"\" in the command line\n This will commit to the new branch.");
                    output.setColor(Color.GREEN);
                    output.setText("Correct!");
                    counter++;
                } else if (counter == 8 && trimmed.equals("git commit -m \"README.md\"")) {
                    instructionsLabel.setText("Instructions:\nType in \"git push\" in the command line\n This will push the new branch\nto the online repository.");
                    counter++;
                    output.setColor(Color.GREEN);
                    output.setText("Well Done!");
                } else if (counter == 9 && trimmed.equals("git push")){
                    instructionsLabel.setText("Instructions:\nType in \"git merge dev master\" in the command line\nThis will merge the two branches\nto the master branch and will\nbring the files from dev to master.");
                    fourthLabel.setText("76d12");
                    fifth.setTransparency(1f);
                    connector4.setTransparency(1f);
                    output.setColor(Color.GREEN);
                    output.setText("Correct!");
                    counter++;
                }else if (counter == 10 && trimmed.equals("git merge dev master")) {
                    instructionsLabel.setText("Instructions:\nType in \"git reset 76d12\" in the command line\n This will reset the master branch to the previous commit.");
                    fourth.setTransparency(1f);
                    connector2.setTransparency(1f);
                    connector5.setTransparency(1f);
                    fifthLabel.setText("i8fe5");
                    output.setColor(Color.GREEN);
                    output.setText("Well Done!");
                    counter++;
                } else if (counter == 11 && trimmed.equals("git reset 76d12")) {
                    instructionsLabel.setText("Instructions:\npress enter to go the next level.");
                    fourth.setTransparency(0f);
                    connector2.setTransparency(0f);
                    connector5.setTransparency(0f);
                    fifthLabel.setText("");
                    output.setColor(Color.GREEN);
                    output.setText("Correct!");
                    counter++;
                } else if (counter == 12) {
                    output.setText("");
                    gameSkin.getFont("commodore-64").getData().setScale(1f);
                    game.setScreen(new Animation(game, new Maze(game, 3), "Welcome to the Git maze,\nFinish the maze to continue your\nmastery of Git"));
                } else {
                    output.setColor(Color.RED);
                    output.setText("Error, please enter the\ncorrect command");
                }
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
