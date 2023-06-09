package com.git.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * The main class for the game. Creates the game and sets the screen to the splashscreen.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @author Sukrit Desai
 * @version 05.23.23
 */
public class GitOdyssey extends Game implements ApplicationListener {

	/** The skin textures used for the game's buttons and fonts. */
	public static Skin gameSkin;

	/**
	 * Creates the game with the splashscreen and sends user to the start of the game.
	 */
	@Override
	public void create () {
		//Sets the skin for the game
		//Source: https://github.com/czyzby/gdx-skins
		gameSkin = new Skin(Gdx.files.internal("Skin/uiskin.json"));

		//Sets the screen to the splashscreen
		setScreen(new SplashScreen());
	}

	public void render() {getScreen().render(Gdx.graphics.getDeltaTime());}

	@Override
	public void resize(int width, int height) {getScreen().resize(width, height);}

	public Screen getScreen() {return (super.getScreen());}


	public void dispose () {}
}