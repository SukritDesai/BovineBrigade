/**
 * Name: Kevin Kolyakov, Sukrit Desai
 * Teacher: Ms Krasteva
 * Description: Main menu with buttons to go to the learning level and the game level.
 */

package com.git.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static com.git.game.Character.health;

public class MultipleChoice implements Screen {

    private final Stage stage;
    private final TextButton firstAnswer;
    private static int questionNum = 0;
    private final String[] answer1= {"Commits changes\nto the repository", "git branch", "git commit", "git push", "git commit", "git commit", "git clone", "Records the changes in the\nworking directory to the \nlocal repository", "Creates a new branch", "git commit"};
    private final String[] answer2= {"Stages changes for commit", "git merge", "git add", "git checkout", "git add", "git reset", "git add", "Creates a copy of a repository", "Changes the current branch", "git add"};
    private final String[] answer3= {"Pushes changes to\na remote repository", "git push", "git checkout", "git add", "git push", "git merge", "git push", "Creates a new branch", "Resets the repository\nto a previous state", "git push"};
    private final String[] answer4= {"Resets the repository\nto a previous state", "git checkout", "git merge", "git commit", "git checkout", "git checkout", "git checkout", "Changes the current branch", "Pushes changes to\na remote repository", "git checkout"};
    private final String[] correctAnswer= {"Stages changes for commit", "git branch", "git merge", "git push", "git checkout", "git reset", "git clone", "Records the changes in the working\ndirectory to the local repository", "Changes the current branch", "git add"};
    private String message = "\nPress enter to coninue";
    private final Game game;

    public MultipleChoice(Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());

        final String[] questionText = {
                "What does the command \"git add\" do?",
                "How do you create a new branch in Git?",
                "What command is used to combine\nchanges from one branch\ninto another branch?",
                "How do you push local commits\nto a remote repository?",
                "How do you switch to a different\nbranch in Git?",
                "How can you undo the most\nrecent commit in Git?",
                "How do you clone a repository\nfrom GitHub?",
                "What does the command\n\"git commit\" do?",
                "What does the command\n\"git checkout\" do?",
                "What do you add to a commit?"};
        final Label question = new Label(questionText[questionNum], GitOdyssey.gameSkin, "default");
        question.setAlignment(Align.center);
        question.setY((float) (Gdx.graphics.getHeight()*2/3.0)+100);
        question.setWidth(Gdx.graphics.getWidth());
        question.setFontScale(2);
        stage.addActor(question);


        firstAnswer = new TextButton(answer1[questionNum],GitOdyssey.gameSkin);
        firstAnswer.setWidth((float) (Gdx.graphics.getWidth()/2.5));
        firstAnswer.setHeight((float) (Gdx.graphics.getHeight()/4.0));
        firstAnswer.setPosition((float) (Gdx.graphics.getWidth()/4.0)-20-firstAnswer.getWidth()/2, ((float)(Gdx.graphics.getHeight()/4)+200-firstAnswer.getHeight()/2));
        final TextButton secondAnswer = new TextButton(answer2[questionNum],GitOdyssey.gameSkin);
        secondAnswer.setWidth((float) (Gdx.graphics.getWidth()/2.5));
        secondAnswer.setHeight((float) (Gdx.graphics.getHeight()/4.0));
        secondAnswer.setPosition((float) (Gdx.graphics.getWidth()/4.0)-20-secondAnswer.getWidth()/2, ((float)(Gdx.graphics.getHeight()/4)-60-secondAnswer.getHeight()/2));
        final TextButton thirdAnswer = new TextButton(answer3[questionNum],GitOdyssey.gameSkin);
        thirdAnswer.setWidth((float) (Gdx.graphics.getWidth()/2.5));
        thirdAnswer.setHeight((float) (Gdx.graphics.getHeight()/4.0));
        thirdAnswer.setPosition((float) (Gdx.graphics.getWidth()/4.0)*3-thirdAnswer.getWidth()/2, ((float)(Gdx.graphics.getHeight()/4)+200-thirdAnswer.getHeight()/2));
        final TextButton fourthAnswer = new TextButton(answer4[questionNum],GitOdyssey.gameSkin);
        fourthAnswer.setWidth((float) (Gdx.graphics.getWidth()/2.5));
        fourthAnswer.setHeight((float) (Gdx.graphics.getHeight()/4.0));
        fourthAnswer.setPosition((float) (Gdx.graphics.getWidth()/4.0)*3-fourthAnswer.getWidth()/2, ((float)(Gdx.graphics.getHeight()/4)-60-fourthAnswer.getHeight()/2));

        firstAnswer.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (answer1[questionNum].equals(correctAnswer[questionNum])) {
                    firstAnswer.setColor(Color.GREEN);
                    message = "\nYou are correct!"+ message;
                }
                else {
                    firstAnswer.setColor(Color.RED);
                    message = "\nYou are incorrect!"+ message;
                    health--;
                }
                firstAnswer.setTouchable(Touchable.disabled);
                secondAnswer.setTouchable(Touchable.disabled);
                thirdAnswer.setTouchable(Touchable.disabled);
                fourthAnswer.setTouchable(Touchable.disabled);
                question.setText(questionText[questionNum]+message);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(firstAnswer);


        secondAnswer.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                if (answer2[questionNum].equals(correctAnswer[questionNum])) {
                    secondAnswer.setColor(Color.GREEN);
                    message = "\nYou are correct!"+ message;
                }
                else {
                    secondAnswer.setColor(Color.RED);
                    message = "\nYou are incorrect!"+ message;
                    health--;
                }
                firstAnswer.setTouchable(Touchable.disabled);
                secondAnswer.setTouchable(Touchable.disabled);
                thirdAnswer.setTouchable(Touchable.disabled);
                fourthAnswer.setTouchable(Touchable.disabled);
                question.setText(questionText[questionNum]+message);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });
        stage.addActor(secondAnswer);

        thirdAnswer.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                if (answer3[questionNum].equals(correctAnswer[questionNum])) {
                    thirdAnswer.setColor(Color.GREEN);
                    message = "\nYou are correct!"+ message;
                }
                else {
                    thirdAnswer.setColor(Color.RED);
                    message = "\nYou are incorrect!"+ message;
                    health--;
                }
                firstAnswer.setTouchable(Touchable.disabled);
                secondAnswer.setTouchable(Touchable.disabled);
                thirdAnswer.setTouchable(Touchable.disabled);
                fourthAnswer.setTouchable(Touchable.disabled);
                question.setText(questionText[questionNum]+message);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });
        stage.addActor(thirdAnswer);


        fourthAnswer.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                if (answer4[questionNum].equals(correctAnswer[questionNum])) {
                    fourthAnswer.setColor(Color.GREEN);
                    message = "\nYou are correct!"+ message;
                }
                else {
                    fourthAnswer.setColor(Color.RED);
                    message = "\nYou are incorrect!"+ message;
                    health--;
                }
                firstAnswer.setTouchable(Touchable.disabled);
                secondAnswer.setTouchable(Touchable.disabled);
                thirdAnswer.setTouchable(Touchable.disabled);
                fourthAnswer.setTouchable(Touchable.disabled);
                question.setText(questionText[questionNum]+message);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });
        stage.addActor(fourthAnswer);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER) && (!firstAnswer.isTouchable())) {
            questionNum++;
            if (questionNum == answer1.length)
                questionNum = 0;
            if (health==0) {
                health = 3;
                game.setScreen(new Popup(game, new MainMenu(game), "You have lost all your health!\nYou have been sent back to the main menu\nto learn more about Git!"));
            } else {
                game.setScreen(new FinalLevel(game));
            }
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}