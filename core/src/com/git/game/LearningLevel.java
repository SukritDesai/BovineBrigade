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

/**
 * The LearningLevel class that implements the level where the user will be taught about Git.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Kevin Kolyakov, Sukrit Desai
 * @version 05.23.23
 * Time Spent: 10 hours
 */
public class LearningLevel implements Screen {
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
    private int counter = 1;
    Game game; // The game that holds the screen

    /**
     * The constructor for the LearningLevel class
     * @param aGame The game that holds the screen (used to switch screens)
     */
    public LearningLevel(Game aGame){
        game = aGame;
    }


    /**
     * The method that shows the screen for the first time it is rendered.
     * It sets up all the variables and objects that are needed for the screen.
     */
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


    /**
     * Renders the screen
     * Also checks for all the user input possibilities, and acts on them accordingly.
     * @param delta The time in seconds since the last render
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        // Check if the user presses enter in the console text field
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (counter==1) {
                instructionsLabel.setText("The first command relating to git that one\nmust learn is git clone which is used to\nclone a repository into your local machine.\nThe command is used as follows:\ngit clone <url>.The url is the url of the\nrepository that you want to clone.\nIn this case, the url is https://odyssey.git\nThe command is used as follows:\ngit clone https://odyssey.git");
            }
            if (consoleTextField.getText().split("\n").length<1){
                output.setColor(Color.RED);
                output.setText("");

            } else if (consoleTextField.getText().split("\n").length > 0) {
                String[] consoleText = consoleTextField.getText().split("\n");
                String trimmed = consoleText[consoleText.length - 1].trim();

                //If the user types in the right command in the console, the next step is shown
                if (counter == 1 && trimmed.equals("git clone https://odyssey.git")) {
                    errorMessage = "Use the command \"git add README.md\"\nto add the file to commit.";
                    instructionsLabel.setText("Instructions:\nThe \"git add\" command stages changes,\npreparing them to be committed in Git repository.\nType in \"git add README.md\" in the command line\nThis will add the file to commit\nand your online project.");
                    first.setTransparency(1f);
                    firstLabel.setText("9e78i");
                    output.setColor(Color.GREEN);
                    output.setText("Cloning into 'odyssey'...\nremote: Enumerating objects: 3, done.\nremote: Counting objects: 100% (3/3), done.\nremote: Total 3 (delta 0), reused 0 (delta 0)\nUnpacking objects: 100% (3/3), done.");
                    counter++;
                } else if (counter == 2 && trimmed.equals("git add README.md")) {
                    errorMessage = "Use the command \"git commit -m \"message\" \"\nto commit the file.";
                    instructionsLabel.setText("Instructions:\nThe \"git commit\" command records staged changes,\ncreating a new commit in the repository's history.\nType in \"git commit -m \"message\" \" in the \ncommand line. This will commit the files to your\nlocal repository and the project.");
                    output.setColor(Color.GREEN);
                    output.setText("Well Done!");
                    counter++;
                } else if (counter == 3 && trimmed.equals("git commit -m \"message\"")) {
                    errorMessage = "Use the command \"git push\" to\nadd the files to the online repository.";
                    instructionsLabel.setText("Instructions:\nThe \"git push\" command uploads\nlocal branch commits to a remote repository in Git.\nType in \"git push\" in the command line\nThis will add the files to the commit(new update)\nof the project.");
                    output.setColor(Color.GREEN);
                    output.setText("Correct!");
                    counter++;
                } else if (counter == 4 && trimmed.equals("git push")) {
                    errorMessage = "Use the command \"git branch \"dev\" \"\nto create a branch.";
                    instructionsLabel.setText("Instructions:\nThe \"git branch\" command allows you to\nview, create, or delete branches in Git.\nType in \"git branch \"dev\" \" in the command line\nThis will create a branch of the repository\nwhere you can make changes to\na different version than the master.");
                    output.setColor(Color.GREEN);
                    output.setText("Well Done!");
                    second.setTransparency(1f);
                    connector1.setTransparency(1f);
                    secondLabel.setText("035cc");
                    counter++;
                } else if (counter == 5 && trimmed.equals("git branch \"dev\"")) {
                    errorMessage = "Use the command \"git checkout dev\" to\nswitch to the new branch.";
                    instructionsLabel.setText("Instructions:\nThe \"git checkout\" command allows you to switch\nbetween branches or restore files\nfrom a specific commit in Git.\nType in \"git checkout dev\" in the command line\nThis will switch the branch you are editing.");
                    third.setTransparency(1f);
                    thirdLabel.setText("e3475");
                    connector3.setTransparency(1f);
                    output.setColor(Color.GREEN);
                    output.setText("Correct!");
                    counter++;
                } else if (counter == 6 && trimmed.equals("git checkout dev")) {
                    errorMessage = "Use the command \"git add README.md\" to\nadd a file to the new branch.";
                    instructionsLabel.setText("Instructions:\nType in \"git add README.md\" in the command line\nThis will add a file to the new branch.");
                    output.setColor(Color.GREEN);
                    output.setText("Well Done!");
                    counter++;
                } else if (counter == 7 && trimmed.equals("git add README.md")) {
                    errorMessage = "Use the command \"git commit -m \"README.md\" \"\nto commit to the new branch.";
                    instructionsLabel.setText("Instructions:\nType in \"git commit -m \"README.md\" \" in the command line\nThis will commit to the new branch.");
                    output.setColor(Color.GREEN);
                    output.setText("Correct!");
                    counter++;
                } else if (counter == 8 && trimmed.equals("git commit -m \"README.md\"")) {
                    errorMessage = "Use the command \"git push\" to push the\nnew branch to the online repository.";
                    instructionsLabel.setText("Instructions:\nType in \"git push\" in the command line\nThis will push the new branch\nto the online repository.");
                    counter++;
                    output.setColor(Color.GREEN);
                    output.setText("Well Done!");
                } else if (counter == 9 && trimmed.equals("git push")){
                    errorMessage = "Use the command \"git merge dev master\"\nto merge the two branches.";
                    instructionsLabel.setText("Instructions:\nThe \"git merge\" command makes changes from one\nbranch into another in Git,\ncombining the commit history and content.\nType in \"git merge dev master\" in the command line\nThis will merge the two branches\nto the master branch and will\nbring the files from dev to master.");
                    fourthLabel.setText("76d12");
                    fifth.setTransparency(1f);
                    connector4.setTransparency(1f);
                    output.setColor(Color.GREEN);
                    output.setText("Correct!");
                    counter++;
                } else if (counter == 10 && trimmed.equals("git merge dev master")) {
                    errorMessage = "Use the command \"git reset 76d12\" to\nreset the master branch to the previous commit.";
                    instructionsLabel.setText("Instructions:\nThe \"git reset\" command undoes commits\nby moving the branch pointer to a previous commit in Git,\nallowing you to discard changes.\nType in \"git reset 76d12\" in the command line\nThis will reset the master branch to the previous\ncommit.");
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
                    game.setScreen(new TransitionAnimation(game, new Maze(game, 3), "Test your Git knowledge in an exhilarating maze!\nUse arrow keys to navigate, conquer challenges, and reach the final level.\nProve your expertise and emerge victorious!"));
                } else {
                    output.setColor(Color.RED);
                    output.setText("Error:\n"+errorMessage);
                }
            }
        }
    }


    /**
     * This method is called when the screen is resized.
     * It adjusts the viewport accordingly to the new screen size.
     * @param width the new width of the screen
     *              (in pixels)
     * @param height the new height of the screen
     *               (in pixels)
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    /**
     * The next three methods are used for other specific cases, but are not implemented for our program.
     * They are only here because this class must implement all of the methods of the Screen interface.
     */
    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}


    /**
     * This method is called when the screen is disposed.
     */
    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
        stage.dispose();
    }
}
